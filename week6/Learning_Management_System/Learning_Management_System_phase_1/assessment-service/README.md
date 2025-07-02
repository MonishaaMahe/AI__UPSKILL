# Assessment Service

Manages quizzes, assignments, submissions, and grading.

## Endpoints
- `POST /assessments` - Create assessment
- `GET /courses/{id}/assessments` - Get assessments for a course
- `POST /assessments/{id}/submissions` - Submit answers
- `GET /submissions?userId={id}` - Get submissions for a user

## Tech Stack
- Spring Boot 3 (WebFlux)
- Spring Data JPA (PostgreSQL)
- RabbitMQ (events) 