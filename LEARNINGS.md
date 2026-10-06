# Aprendizajes del stack

Notas de teoría vistas en el proyecto. Documento vivo: se amplía en cada paso.
Lenguaje y código en inglés; notas en español.

## 1. Java 17

- Entidad como clase con getters/setters, constructor vacío (lo exige JPA) y constructor de dominio.
- DTO como `record`: inmutable, sin boilerplate, ideal para exponer datos en la API.
  - `record AnimalDTO(Long id, String commonName, ...) {}`
  - Motivo: la entidad es persistencia; el DTO es contrato de API. Separarlos evita filtrar detalles internos.

## 2. Spring Boot

- `@SpringBootApplication` arranca el contexto (escaneo de componentes + autoconfiguración).
- Seed con `data.sql` (`src/main/resources/`): 103 `MERGE INTO animal_entity (...) KEY(id) VALUES (...)` con IDs explícitos 1–103 en orden lógico (invertebrados, peces, anfibios/reptiles, aves y mamíferos).
- `MERGE INTO ... KEY(id)` es el upsert de H2: reejecutar el seed no duplica (idempotente). Importa porque cada `ApplicationContext` de test reejecuta `data.sql` sobre el mismo H2 en memoria.
- Config para que el SQL corra tras crear Hibernate el esquema: `spring.jpa.defer-datasource-initialization=true` + `spring.sql.init.mode=always`.
- Ventaja frente al `CommandLineRunner` anterior: seed declarativo (SQL visible en review, editable sin compilar) y sin lógica de carga en el código.

## 3. Spring Data JPA + H2

- `@Entity` + `@Table(name = "animal_entity")` = tabla con nombre fijo (no depende de la estrategia de nombrado).
- `@Id` + `@GeneratedValue(IDENTITY)` = clave autogenerada por la BD.
- `@Column(name = "...", nullable = ..., length = ...)` por campo: fija el nombre snake_case (`common_name`, `scientific_name`, ...), la obligatoriedad (`nullable = false` en todo salvo `image_url`) y el tamaño (`length`: 100 nombres/hábitat, 50 dieta/estado, 500 descripción, 255 URL). Sin esto Hibernate lo infiere, pero explícito protege el esquema y documenta el dominio.
- `interface AnimalRepository extends JpaRepository<Animal, Long>` da gratis `findAll()`, `findById()`, `save()` sin implementar nada.
- `findById()` devuelve `Optional<Animal>`: obliga a decidir qué pasa si no existe (ver punto 5).
- Config: `jdbc:h2:mem:lifestream`, `ddl-auto=update`, `show-sql=true`.

## 4. Spring Web (REST)

- `@RestController` = `@Controller` + `@ResponseBody`: el retorno se serializa a JSON con Jackson.
- `@GetMapping("/api/animals")` para lista; `@GetMapping("/api/animals/{id}")` + `@PathVariable Long id` para detalle.
  - Diferencia con `@RequestMapping`: es genérica (hay que indicar método); `@GetMapping` es su atajo específico para GET.
- `ResponseEntity.ok(...)` envuelve el DTO con estado 200 en el controlador; la conversión entidad → DTO (`stream().map(this::toDTO).toList()`) vive en el servicio.

## 5. Gestión de errores

- `AnimalNotFoundException extends RuntimeException` + `@ResponseStatus(HttpStatus.NOT_FOUND)` convierte la excepción en un 404 automáticamente.
- En el servicio: `findById(id).map(...).orElseThrow(() -> new AnimalNotFoundException(id))`.
- Patrón `Optional.map().orElseThrow()`: transforma si existe, falla con excepción de dominio si no.

## 6. Capa de servicio

- `@Service` en `AnimalServiceImpl` marca lógica de aplicación (entre controlador y repositorio); el controlador depende de la interfaz `AnimalService`.
- El controlador solo gestiona HTTP (`@GetMapping`, `ResponseEntity`); el servicio consulta con `AnimalRepository` y mapea entidad → `AnimalDTO` con `toDTO`.
- Ventaja: el controlador queda fino, la conversión se reutiliza y el servicio se puede probar aislado con mocks (se mockea la interfaz).

## 7. Pruebas
- `AnimalEntityControllerTest`: `@SpringBootTest` + `@AutoConfigureMockMvc` con 6 tests (página por defecto 20/`totalElements=103`/sin `imageUrl` en lista, `?name=lobo`, `?diet=Carnívoro` → 40, detalle `/1` completo, `/999` → 404, `/count` → 103).
- Ojo Boot 4: `@AutoConfigureMockMvc` se movió a `org.springframework.boot.webmvc.test.autoconfigure` (artefacto `spring-boot-webmvc-test`, scope test). El import antiguo `boot.test.autoconfigure.web.servlet` ya no existe.
- Cada clase de test levanta su propio `ApplicationContext`, y cada uno reejecuta `data.sql` sobre el mismo H2 en memoria; como el seed usa `MERGE INTO ... KEY(id)`, el total sigue en 103.

## 8. Búsqueda y filtros
- `@RequestParam(required = false)` en el controlador para filtros opcionales (`name`, `habitat`, `diet`, `conservationStatus`).
- `@Query` con JPQL en el repositorio: un solo método `search(...)` con condiciones `(:param IS NULL OR ...)` para filtros combinables sin explosión de métodos derivados. Usa **proyección por interfaz** (`SELECT a.id AS id, a.commonName AS commonName` → `AnimalSummaryProjection` con `getId()`/`getCommonName()`): la BD devuelve solo 2 columnas, sin hidratar entidades y sin clase DTO que instanciar.
- `name` usa `LIKE %...%` insensible a mayúsculas sobre `commonName` y `scientificName`; el resto usa igualdad insensible a mayúsculas.
- El servicio normaliza `null`/vacío (`isBlank → null`) para que `?diet=` equivalga a no filtrar.

## 9. Imágenes (`imageUrl`)
- Campo `String` nullable en `AnimalEntity` y `AnimalDTO`: con `ddl-auto=update` Hibernate añade la columna sin migraciones.
- Constructor de 6 campos conservado (delega con `imageUrl=null`) + constructor de 7 campos para las fichas con foto.
- Local en vez de hotlink: fotos en `src/main/resources/static/images/animals/` (~4 MB), servidas en `/images/animals/*.jpg` sin código (convención de Spring Boot para estáticos). `imageUrl` guarda la ruta local.
- Descarga: nombre de fichero sacado de la URL de Commons → `Special:FilePath/<nombre>?width=800` (miniatura ~800px) → guardado con slug (`mantis-religiosa.jpg`). Sin PIL/ImageMagick en el entorno, así se evita traer originales de varios MB.
- Origen: Wikimedia Commons vía resúmenes de Wikipedia (`originalimage.source`). Ojo con el `rate limit` (429) al verificar en masa: espaciar peticiones.
- Estado: 103/103 con imagen. Lote 2 (80 fotos): 54 OK a la primera, 26 con 404 porque Wikipedia devuelve algunas "originales" ya como `thumb/.../3840px-<fichero>` y hay que quitar el prefijo de ancho para obtener el nombre real.

## 10. Proyección para listas
- Detalle: `AnimalDTO` (`record` con ficha completa); lista: `AnimalSummaryProjection` (interfaz con `getId()` + `getCommonName()`).
- El servicio devuelve el resultado del repositorio tal cual en `searchAnimals` (ya viene proyectado); mapea con `toDTO` solo en `getAnimalById`; el repositorio devuelve entidades únicamente en el detalle (`findById`).
- Efecto medido: `GET /api/animals` pasa de ~60 KB a ~4 KB para 103 fichas, y la lista ni conoce las `imageUrl`, así que ninguna vista puede disparar descargas de fotos desde el listado.

## 11. Paginación
- Repositorio: `search(...)` recibe `Pageable` y devuelve `Page<AnimalSummaryProjection>`. Con `@Query` de proyección hay que dar `countQuery` explícito (Spring no lo deriva de un `SELECT` parcial).
- Servicio: propaga el `Pageable`; el controlador lo recibe con `@PageableDefault(size = 20, sort = "id")` (`?page=` 0-based, `?size=`, `?sort=`).
- Respuesta JSON en Spring Boot 4: formato plano (`content`, `totalElements`, `totalPages`, `size`, `number`, `first`, `last`), sin envoltorio `page`.
- Medido: 103 fichas → 6 páginas de 20; `?diet=Carnívoro` → 40 resultados paginables.

### Guía rápida de búsquedas
```bash
GET /api/animals                                  # todo, pág. 0 de 20 ordenada por nombre
GET /api/animals?size=100                         # todo en una página (103 caben)
GET /api/animals?page=1&size=5                    # segunda página de 5
GET /api/animals?name=lobo                        # contiene, en común y científico
GET /api/animals?diet=Carnívoro                   # igualdad exacta (ojo: í → %C3%AD en curl)
GET /api/animals?conservationStatus=Vulnerable
GET /api/animals?habitat=Sabanas
GET /api/animals?diet=Carnívoro&conservationStatus=Vulnerable   # combinados (AND)
GET /api/animals?diet=Carnívoro&size=100          # filtro + todo en una página
GET /api/animals?sort=scientificName,desc&size=3  # ordenar por otro campo
GET /api/animals/1                                # detalle completo (único que trae imageUrl)
GET /api/animals/999                              # -> 404
GET /api/animals/count                            # total guardado (103), sin colisión con /{id}: Spring prioriza la ruta exacta
```
- `name` es el único parcial (`LIKE %...%`); el resto exige el valor exacto (mayúsculas da igual).
- Vacío equivale a ausente: `?diet=` no filtra.
- La lista devuelve `id` + `commonName`; la foto solo sale en el detalle.

## 12. OpenAPI (springdoc)
- Dependencia `org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.1` (línea 3.x, compatible con Spring Boot 4; la 2.x es para Boot 3).
- `config/OpenApiConfig` expone un bean `OpenAPI` con `Info` (título, descripción, versión); el resto lo deriva springdoc de las anotaciones y firmas.
- Controlador anotado con `@Tag` (grupo), `@Operation` (resumen), `@Parameter` (filtros + `id`) y `@ApiResponse` 200/404 en el detalle; `AnimalDTO` con `@Schema` (descripción + ejemplos).
- Endpoints generados sin código: JSON en `/v3/api-docs`, UI en `/swagger-ui/index.html`.
- En producción se desactivan con `springdoc.api-docs.enabled=false` y `springdoc.swagger-ui.enabled=false`.

## 13. Vista Thymeleaf (maestro-detalle)
- Dependencia `spring-boot-starter-thymeleaf`, sin tocar los endpoints JSON.
- `controller/AnimalViewController` (`@Controller`, no `@RestController`): `GET /` acepta los mismos filtros + `Pageable` que la API, llama al servicio directamente (sin HTTP a sí mismo) y devuelve el nombre de plantilla `"animals"` con la `Page` y los filtros en el `Model`.
- `templates/animals.html`: buscador por nombre + desplegables de hábitat/dieta/estado (`<select>` con las opciones `DISTINCT` de la BD y `th:selected` para conservar el filtro elegido), links de paginación que conservan filtros (`@{/(page=..., size=..., name=..., ...)}`; Thymeleaf omite los nulos) y panel `#detail` relleno por `fetch('/api/animals/' + id)`.
- Patrón confirmado: la lista solo necesita la proyección ligera; la ficha completa (con `imageUrl`) viaja solo al seleccionar.
- Tests: `AnimalViewControllerTest` (4 tests: `/` → 200 HTML con la lista, `/?name=lobo` filtra, desplegables con opciones, dieta seleccionada preservada).
