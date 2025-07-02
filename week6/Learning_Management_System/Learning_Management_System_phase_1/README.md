# Learning Management System (LMS) Microservices

This project is a microservices-based Learning Management System built with Spring Boot 3, WebFlux, PostgreSQL, Redis, RabbitMQ, and Spring Cloud Gateway. Each service is independently deployable and communicates via REST and RabbitMQ events.

## Services
- **user-service**: Manages users (students, instructors, admins)
- **course-service**: Manages courses, modules, lessons
- **enrollment-service**: Handles enrollments and progress
- **assessment-service**: Manages quizzes, assignments, grading
- **notification-service**: Sends emails and push notifications
- **api-gateway**: API entry point and routing

## Tech Stack
- Spring Boot 3 (WebFlux)
- Spring Data JPA (PostgreSQL)
- Redis (caching, session)
- RabbitMQ (messaging)
- Spring Cloud Gateway
- Docker, Kubernetes

## Running Locally
1. Install Docker and Docker Compose
2. Run: `docker-compose up --build`
3. Access API Gateway at `http://localhost:8080`

## Deployment
- Kubernetes manifests are in the `k8s/` directory.

---

Each service has its own README for details. 