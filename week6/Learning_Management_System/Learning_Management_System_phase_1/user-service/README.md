# User Service

Manages users (students, instructors, admins), authentication, and roles.

## Endpoints
- `POST /users` - Register user
- `POST /auth/login` - Login
- `GET /users/{id}` - Get user by ID
- `GET /users/{id}/roles` - Get user roles

## Tech Stack
- Spring Boot 3 (WebFlux)
- Spring Data JPA (PostgreSQL)
- Redis (sessions)
- RabbitMQ (events) 