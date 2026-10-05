# Memoria del proyecto Lifestream

## Objetivo

Construir una API de consulta del mundo animalEntity, con un estilo Pokédex pero basada en animales reales. El proyecto se utiliza como escenario de aprendizaje para mejorar habilidades de programación.

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

Verificado el 5 de octubre de 2026: `./mvnw test` → `BUILD SUCCESS` (7 tests: `contextLoads` + 6 `MockMvc`).

- Paquete base: `com.alroru.lifestream`. La aplicación se llama `lifestream`.
- H2 en memoria (`jdbc:h2:mem:lifestream`), `ddl-auto=update`, `show-sql=true`.
- Paquete `model/entity`: entidad `AnimalEntity` (`@Entity` + `@Table(name="animal_entity")`) con `id` (`@Id` + `@GeneratedValue IDENTITY`) y campos `commonName`, `scientificName`, `habitat`, `diet`, `conservationStatus`, `description`, `imageUrl` (todos `String`, con `@Column` explícito: `name` snake_case, `nullable=false` salvo `image_url`, `length` 50–500 según campo).
- Paquete `repository`: `AnimalRepository extends JpaRepository<AnimalEntity, Long>` + `search(...)` con `@Query` JPQL y proyección por interfaz (`SELECT a.id AS id, a.commonName AS commonName` → `AnimalSummaryProjection` + `countQuery`, filtros combinables `name`, `habitat`, `diet`, `status`); devuelve `Page<AnimalSummaryProjection>` con `Pageable`.
- Seed SQL: `src/main/resources/data.sql` con 103 `MERGE INTO animal_entity ... KEY(id)` (idempotente, en orden lógico: invertebrados, peces, anfibios/reptiles, aves y mamíferos), todos con `imageUrl` local (`/images/animals/*.jpg`, 103 ficheros en `src/main/resources/static/`). Se ejecuta tras crear Hibernate el esquema (`spring.jpa.defer-datasource-initialization=true`, `spring.sql.init.mode=always`).
- Paquete `model/dto`: `AnimalDTO` (`record` con ficha completa, 8 campos). Proyección `repository/AnimalSummaryProjection` (interfaz con `getId()` + `getCommonName()` para la lista).
- Paquete `controller`: `AnimalController` fino, delega en el servicio (`GET /api/animals` → `Page<AnimalSummaryProjection>` con `@RequestParam` opcionales + `@PageableDefault(size=20, sort=id)`, `GET /api/animals/count` → total, `GET /api/animals/{id}` → `AnimalDTO` completo con `ResponseEntity.ok(...)`).
- Paquete `service`: interfaz `AnimalService` + `AnimalServiceImpl` (`@Service`) con `searchAnimals(...)` (devuelve la `Page` proyectada del repositorio; normaliza blancos a `null`), `getAnimalById()` (devuelve `AnimalDTO` completo vía `toDTO`), `countAnimals()` (`animalRepository.count()`); lanza `AnimalNotFoundException` si no existe. El controlador depende de la interfaz.
- Paquete `model/exception`: `AnimalNotFoundException` con `@ResponseStatus(NOT_FOUND)` para el 404.
- Pruebas: `LifestreamApplicationTests.contextLoads` + `controller/AnimalEntityControllerTest` (6 tests `MockMvc`: página por defecto, búsqueda por nombre, filtro por dieta, detalle 200 con `imageUrl`, detalle 404, `count`). Requiere `spring-boot-starter-test` y `spring-boot-webmvc-test` en el pom.
- Docs vivas: `README.md` (uso del proyecto) y `LEARNINGS.md` (teoría del stack).
- Árbol limpio. Últimos commits: seed SQL + interfaz de servicio + columnas JPA + proyección, `0d83f52` (fotos y orden del catálogo), `a1b11dd` (filtros, paginación, imágenes locales y tests).

## Ruta de aprendizaje prevista

1. ~~Modelar el dominio `AnimalEntity` y conocer los tipos de datos y conceptos básicos.~~ Hecho.
2. ~~Crear la entidad JPA y el repositorio de Spring Data.~~ Hecho.
3. ~~Preparar e insertar datos iniciales.~~ Hecho.
4. ~~Exponer los endpoints REST de lista y detalle.~~ Hecho (lista + detalle con DTO).
5. ~~Gestionar errores y recursos no encontrados.~~ Hecho (`AnimalNotFoundException` → 404).
6. ~~Añadir pruebas unitarias y de integración.~~ Hecho (`AnimalEntityControllerTest` con `MockMvc`: 6 tests).
7. ~~Incorporar búsqueda, filtros y paginación.~~ Hecho (filtros combinables + proyección + `Page` con `countQuery`).

## Próxima tarea

Vista estática en `src/main/resources/static/` (buscador → lista → detalle con imagen + tabla).

## Convenciones y verificación

- Mantener el código y los nombres en inglés; la comunicación con el usuario permanece en español.
- Organizar el código por paquetes y capas a medida que crece la aplicación.
- Después de cada cambio funcional, ejecutar `./mvnw test` y explicar el resultado.
