# Lifestream

API de consulta del mundo animalEntity con estilo Pokédex pero con animales reales.
Proyecto de aprendizaje: Java + Spring Boot.

## Stack

- Java 17
- Spring Boot 4.1.1
- Spring Web (API REST)
- Spring Data JPA (persistencia)
- H2 en memoria (desarrollo)
- JUnit 5 (pruebas)
- Maven (construcción, `./mvnw` incluido)

## Estructura

- `model/Animal` — entidad JPA (`id`, `commonName`, `scientificName`, `habitat`, `diet`, `conservationStatus`, `description`, `imageUrl` nullable con foto de Wikimedia Commons).
- `repository/AnimalRepository` — `JpaRepository<Animal, Long>`, sin métodos propios.
- `dto/AnimalDTO` — `record` inmutable con la ficha completa (detalle).
- `repository/AnimalSummaryProjection` — interfaz con `getId()` + `getCommonName()` (lista).
- `controller/AnimalController` — endpoints REST (delega en el servicio).
- `service/AnimalService` (interfaz) + `service/AnimalServiceImpl` — lógica de aplicación: consulta el repositorio y convierte entidad → DTO.
- `exception/AnimalNotFoundException` — error 404 cuando no existe el id.
- `data.sql` — 103 `MERGE INTO` idempotentes al arrancar (invertebrados, peces, anfibios/reptiles, aves y mamíferos), todos con `imageUrl` local (`/images/animals/*.jpg`, ficheros en `src/main/resources/static/`, fotos de Wikimedia Commons).

## Imágenes

Fotos de animales en `src/main/resources/static/images/animals/` (103 ficheros, ~21 MB). Spring las sirve en `/images/animals/<ficha>.jpg` sin código adicional. Origen: Wikimedia Commons (vía miniaturas `Special:FilePath?width=800`); revisar la licencia de cada archivo si el proyecto se publica.

## Requisitos

- Java 17 (Temurin `17.0.20.1+1`, `JAVA_HOME` apuntando ahí).
- Sin necesidad de instalar Maven: se usa `./mvnw`.
- Tras clonar en Linux/WSL: `chmod +x mvnw`.

## Cómo ejecutar

```bash
./mvnw spring-boot:run
```

La API queda en `http://localhost:8080`.

## Cómo probar

```bash
./mvnw test
```

## Endpoints

### `GET /api/animals`

Devuelve una página de `id` + `commonName` (lista ligera). Filtros opcionales y combinables + paginación:

- Filtros: `name` (contiene en `commonName`/`scientificName`), `habitat`, `diet`, `conservationStatus` (igualdad exacta; todo insensible a mayúsculas).
- Paginación: `page` (0-based), `size`, `sort`. Por defecto `size=20` ordenado por `id`.

```bash
curl "http://localhost:8080/api/animals?name=lobo"
curl "http://localhost:8080/api/animals?page=1&size=5"
curl "http://localhost:8080/api/animals?diet=Carnivoro&size=100"
```

Respuesta (formato plano de Spring Boot 4, sin envoltorio `page`):

```json
{"content": [{"id": 2, "commonName": "Lobo ibérico"}], "totalElements": 103, "totalPages": 6, "size": 20, "number": 0, "first": true, "last": false}
```

Respuesta (abreviada):

```json
[
  {
    "id": 1,
    "commonName": "Zorro rojo",
    "scientificName": "Vulpes vulpes",
    "habitat": "Bosques y praderas",
    "diet": "Omnívoro",
    "conservationStatus": "Preocupación menor",
    "description": "..."
  }
]
```

### `GET /api/animals/count`

Devuelve el total de animales guardados (número plano).

```bash
curl http://localhost:8080/api/animals/count   # -> 103
```

### `GET /api/animals/{id}`

Detalle de un animalEntity. Si no existe, devuelve `404`.

```bash
curl http://localhost:8080/api/animals/1
curl -i http://localhost:8080/api/animals/999  # -> 404
```

## Configuración

`src/main/resources/application.properties`:

- `spring.datasource.url=jdbc:h2:mem:lifestream`
- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.defer-datasource-initialization=true` (ejecuta `data.sql` tras crear Hibernate el esquema)
- `spring.sql.init.mode=always`
- `spring.jpa.show-sql=true`

Los datos se reinician en cada arranque (H2 en memoria + `data.sql`).

## Estado del MVP

- [x] `GET /api/animals` (+ filtros `name`, `habitat`, `diet`, `conservationStatus`, paginado, proyección a resumido)
- [x] `GET /api/animals/{id}` (+ 404)
- [x] `GET /api/animals/count`
- [ ] Pruebas propias del dominio (pendiente)
