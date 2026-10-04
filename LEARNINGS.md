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
- `CommandLineRunner` (`config/DataInitializer`) se ejecuta una vez al arrancar. Patrón: inyección por constructor del repositorio + `saveAll(List.of(...))` para carga masiva.
- Nota: con H2 en memoria el `run()` se repite en cada arranque; en producción habría que comprobar si ya hay datos.
- Nota: con H2 en memoria el `run()` se repite en cada arranque; en producción habría que comprobar si ya hay datos.

## 3. Spring Data JPA + H2

- `@Entity` + `@Id` + `@GeneratedValue(IDENTITY)` = tabla con clave autogenerada.
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

- `@Service` marca lógica de aplicación (entre controlador y repositorio).
- El controlador solo gestiona HTTP (`@GetMapping`, `ResponseEntity`); el servicio consulta con `AnimalRepository` y mapea entidad → `AnimalDTO` con `toDTO`.
- Ventaja: el controlador queda fino, la conversión se reutiliza y el servicio se puede probar aislado con mocks.

## 7. Pruebas
- `AnimalControllerTest`: `@SpringBootTest` + `@AutoConfigureMockMvc` con 6 tests (página por defecto 20/`totalElements=103`/sin `imageUrl` en lista, `?name=lobo`, `?diet=Carnívoro` → 40, detalle `/4` completo, `/999` → 404, `/count` → 103).
- Ojo Boot 4: `@AutoConfigureMockMvc` se movió a `org.springframework.boot.webmvc.test.autoconfigure` (artefacto `spring-boot-webmvc-test`, scope test). El import antiguo `boot.test.autoconfigure.web.servlet` ya no existe.
- Ambas clases de test comparten el mismo contexto Spring (misma configuración), así que `DataInitializer` inserta las 103 fichas una sola vez.

## 8. Búsqueda y filtros
- `@RequestParam(required = false)` en el controlador para filtros opcionales (`name`, `habitat`, `diet`, `conservationStatus`).
- `@Query` con JPQL en el repositorio: un solo método `search(...)` con condiciones `(:param IS NULL OR ...)` para filtros combinables sin explosión de métodos derivados. Usa **proyección con expresión constructora** (`SELECT new ...AnimalSummaryDTO(a.id, a.commonName)`): la BD devuelve solo 2 columnas, sin hidratar entidades.
- `name` usa `LIKE %...%` insensible a mayúsculas sobre `commonName` y `scientificName`; el resto usa igualdad insensible a mayúsculas.
- El servicio normaliza `null`/vacío (`isBlank → null`) para que `?diet=` equivalga a no filtrar.

## 9. Imágenes (`imageUrl`)
- Campo `String` nullable en `Animal` y `AnimalDTO`: con `ddl-auto=update` Hibernate añade la columna sin migraciones.
- Constructor de 6 campos conservado (delega con `imageUrl=null`) + constructor de 7 campos para las fichas con foto.
- Local en vez de hotlink: fotos en `src/main/resources/static/images/animals/` (~4 MB), servidas en `/images/animals/*.jpg` sin código (convención de Spring Boot para estáticos). `imageUrl` guarda la ruta local.
- Descarga: nombre de fichero sacado de la URL de Commons → `Special:FilePath/<nombre>?width=800` (miniatura ~800px) → guardado con slug (`mantis-religiosa.jpg`). Sin PIL/ImageMagick en el entorno, así se evita traer originales de varios MB.
- Origen: Wikimedia Commons vía resúmenes de Wikipedia (`originalimage.source`). Ojo con el `rate limit` (429) al verificar en masa: espaciar peticiones.
- Estado: 23/103 con imagen (invertebrados). Pendientes: peces, anfibios/reptiles, aves, mamíferos + 3 iniciales.

## 10. DTO resumido para listas
- Dos DTO: `AnimalDTO` (ficha completa, detalle) y `AnimalSummaryDTO` (`id` + `commonName`, lista).
- El servicio devuelve el resultado del repositorio tal cual en `searchAnimals` (ya viene proyectado); mapea con `toDTO` solo en `getAnimalById`; el repositorio devuelve entidades únicamente en el detalle (`findById`).
- Efecto medido: `GET /api/animals` pasa de ~60 KB a ~4 KB para 103 fichas, y la lista ni conoce las `imageUrl`, así que ninguna vista puede disparar descargas de fotos desde el listado.

## 11. Paginación
- Repositorio: `search(...)` recibe `Pageable` y devuelve `Page<AnimalSummaryDTO>`. Con `@Query` de proyección hay que dar `countQuery` explícito (Spring no lo deriva de un `SELECT new`).
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
GET /api/animals/4                                # detalle completo (único que trae imageUrl)
GET /api/animals/999                              # -> 404
GET /api/animals/count                            # total guardado (103), sin colisión con /{id}: Spring prioriza la ruta exacta
```
- `name` es el único parcial (`LIKE %...%`); el resto exige el valor exacto (mayúsculas da igual).
- Vacío equivale a ausente: `?diet=` no filtra.
- La lista devuelve `id` + `commonName`; la foto solo sale en el detalle.
