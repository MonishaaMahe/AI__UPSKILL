# Enrollment Service

Handles student enrollments and progress tracking.

## Endpoints
- `POST /enrollments` - Enroll a student
- `GET /enrollments?userId={id}` - Get enrollments for a user
- `GET /enrollments/{id}/progress` - Get progress for an enrollment
- `POST /enrollments/{id}/progress` - Update progress

## Tech Stack
- Spring Boot 3 (WebFlux)
- Spring Data JPA (PostgreSQL)
- RabbitMQ (events) 