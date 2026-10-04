# Memoria del proyecto Lifestream

## Objetivo

Construir una API de consulta del mundo animal, con un estilo Pokédex pero basada en animales reales. El proyecto se utiliza como escenario de aprendizaje para mejorar habilidades de programación.

## Modalidad de trabajo

- Comunícate siempre en español.
- Ritmo más rápido, sin pararse en explicaciones base salvo que se pida.
- El código lo escribe el agente; el usuario revisa y dirige.
- Dos archivos Markdown vivos en la raíz:
  - `README.md` → qué es el proyecto y cómo se usa.
  - `LEARNINGS.md` → enseñanzas y teoría del stack visto.
- Mantener ambos actualizados cuando el proyecto avance.

## Stack y alcance inicial

- Java 17.
- Spring Boot 4.1.1.
- Spring Web.
- Spring Data JPA.
- H2 como base de datos en memoria.
- JUnit para pruebas automatizadas.

Endpoints previstos para el MVP:

- `GET /api/animals`
- `GET /api/animals/{id}`

Datos previstos: identificador, nombre común, nombre científico, hábitat, dieta, estado de conservación y descripción.

## Estado actual

Verificado el 2 de octubre de 2026: `./mvnw test` → `BUILD SUCCESS` (verificar de nuevo tras los cambios sin commitear descritos abajo).

- Paquete base: `com.alroru.lifestream`. La aplicación se llama `lifestream`.
- H2 en memoria (`jdbc:h2:mem:lifestream`), `ddl-auto=update`, `show-sql=true`.
- Paquete `model`: entidad `Animal` (`@Entity`) con `id` (`@Id` + `@GeneratedValue IDENTITY`) y campos `commonName`, `scientificName`, `habitat`, `diet`, `conservationStatus`, `description`, `imageUrl` (nullable, todos `String`). Constructor vacío, constructor de 6 campos (delega con `imageUrl=null`) y constructor de 7 campos.
- Paquete `repository`: `AnimalRepository extends JpaRepository<Animal, Long>` + `search(...)` con `@Query` JPQL y proyección (`SELECT new AnimalSummaryDTO(a.id, a.commonName)` + `countQuery`, filtros combinables `name`, `habitat`, `diet`, `status`); devuelve `Page<AnimalSummaryDTO>` con `Pageable`.
- Paquete `config`: `DataInitializer implements CommandLineRunner` con inyección por constructor; guarda 103 animales con `saveAll(List.of(...))` (zorro rojo, lobo ibérico, lince ibérico + 100 de invertebrados, peces, anfibios/reptiles, aves y mamíferos). Los 23 invertebrados llevan `imageUrl` local (`/images/animals/*.jpg` en `src/main/resources/static/`); el resto `null` (bloques pendientes: peces, anfibios/reptiles, aves, mamíferos). Nota: inserta siempre, sin comprobar si la tabla ya tiene datos.
- Paquete `dto`: `AnimalDTO` (ficha completa, 8 campos) y `AnimalSummaryDTO` (`id` + `commonName` para la lista).
- Paquete `controller`: `AnimalController` fino, delega en el servicio (`GET /api/animals` → `Page<AnimalSummaryDTO>` con `@RequestParam` opcionales + `@PageableDefault(size=20, sort=id)`, `GET /api/animals/count` → total, `GET /api/animals/{id}` → `AnimalDTO` completo con `ResponseEntity.ok(...)`).
- Paquete `service`: `AnimalService` (`@Service`) con `searchAnimals(...)` (devuelve la `Page` proyectada del repositorio; normaliza blancos a `null`), `getAnimalById()` (devuelve `AnimalDTO` completo vía `toDTO`), `countAnimals()` (`animalRepository.count()`); lanza `AnimalNotFoundException` si no existe.
- Paquete `exception`: `AnimalNotFoundException` con `@ResponseStatus(NOT_FOUND)` para el 404.
- Pruebas: `LifestreamApplicationTests.contextLoads` + `AnimalControllerTest` (6 tests `MockMvc`: página por defecto, búsqueda por nombre, filtro por dieta, detalle 200 con `imageUrl`, detalle 404, `count`). Requiere `spring-boot-starter-test` y `spring-boot-webmvc-test` en el pom.
- Docs nuevas (4 oct 2026, sin commitear): `README.md` (uso del proyecto) y `LEARNINGS.md` (teoría del stack).
- Cambios sin commitear (4 oct 2026): `AGENTS.md`, `AnimalController` (DTO + detalle + 404), nuevos `dto/`, `exception/`, `README.md`, `LEARNINGS.md`. Último commit: `d320512 primer commit`.

Pendiente: pruebas propias del dominio (integración con MockMvc para 200 + 404).

## Ruta de aprendizaje prevista

1. ~~Modelar el dominio `Animal` y conocer los tipos de datos y conceptos básicos.~~ Hecho.
2. ~~Crear la entidad JPA y el repositorio de Spring Data.~~ Hecho.
3. ~~Preparar e insertar datos iniciales.~~ Hecho.
4. ~~Exponer los endpoints REST de lista y detalle.~~ Hecho (lista + detalle con DTO).
5. ~~Gestionar errores y recursos no encontrados.~~ Hecho (`AnimalNotFoundException` → 404).
6. ~~Añadir pruebas unitarias y de integración.~~ Hecho (`AnimalControllerTest` con `MockMvc`: 6 tests).
7. ~~Incorporar búsqueda, filtros y paginación.~~ Hecho (filtros combinables + proyección + `Page` con `countQuery`).

## Próxima tarea

Vista estática en `src/main/resources/static/` (buscador → lista → detalle con imagen + tabla). Antes, decidir el fallback para los 80 `imageUrl` en `null` (placeholder local propuesto).

## Convenciones y verificación

- Mantener el código y los nombres en inglés; la comunicación con el usuario permanece en español.
- Organizar el código por paquetes y capas a medida que crece la aplicación.
- Después de cada cambio funcional, ejecutar `./mvnw test` y explicar el resultado.
