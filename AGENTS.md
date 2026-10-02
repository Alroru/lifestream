# Memoria del proyecto Lifestream

## Objetivo

Construir una API de consulta del mundo animal, con un estilo Pokédex pero basada en animales reales. El proyecto se utiliza como escenario de aprendizaje para mejorar habilidades de programación.

## Modalidad de trabajo

- Comunícate siempre en español.
- El usuario quiere aprender mediante tareas pequeñas y progresivas.
- Presentar un único objetivo por tarea y explicar brevemente el concepto necesario.
- El usuario escribe el código. Actuar como planificador, guía y revisor; no entregar una solución completa salvo que la solicite expresamente.
- Ayudar a interpretar errores de compilación y pruebas sin saltarse los pasos de aprendizaje.
- Al terminar cada tarea, comprobar el resultado, explicar lo aprendido y proponer solo la siguiente tarea.

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

- El proyecto Maven inicial ya está creado en esta carpeta.
- Paquete base: `com.alroru.lifestream`.
- La aplicación se llama `lifestream`.
- H2 está configurada como `jdbc:h2:mem:lifestream` y Hibernate usa `ddl-auto=update`.
- Solo existen la aplicación inicial, su prueba de contexto y la configuración básica; todavía no se ha implementado el dominio animal.
- El 23 de septiembre de 2026 el usuario ejecutó correctamente `chmod +x mvnw` y `./mvnw test`, obteniendo `BUILD SUCCESS`.

## Ruta de aprendizaje prevista

1. Modelar el dominio `Animal` y conocer los tipos de datos y conceptos básicos.
2. Crear la entidad JPA y el repositorio de Spring Data.
3. Preparar e insertar datos iniciales.
4. Exponer los endpoints REST de lista y detalle.
5. Gestionar errores y recursos no encontrados.
6. Añadir pruebas unitarias y de integración.
7. Incorporar búsqueda, filtros y paginación.

## Próxima tarea

Comenzar por el modelo de dominio `Animal`. Antes de añadir anotaciones de persistencia, decidir y explicar sus campos y tipos Java. Mantener esta primera tarea centrada en Java y en el diseño del dominio.

## Convenciones y verificación

- Mantener el código y los nombres en inglés; la comunicación con el usuario permanece en español.
- Organizar el código por paquetes y capas a medida que crece la aplicación.
- Después de cada cambio funcional, ejecutar `./mvnw test` y explicar el resultado.
