# Course Service

Manages courses, modules, and lessons.

## Endpoints
- `POST /courses` - Create course
- `GET /courses/{id}` - Get course by ID
- `GET /courses/{id}/modules` - Get modules for a course
- `GET /modules/{id}/lessons` - Get lessons for a module

## Tech Stack
- Spring Boot 3 (WebFlux)
- Spring Data JPA (PostgreSQL)
- RabbitMQ (events) 