# Notification Service

Sends emails and push notifications based on events.

## Endpoints
- `POST /notifications` - Send notification
- `GET /notifications?userId={id}` - Get notifications for a user

## Tech Stack
- Spring Boot 3 (WebFlux)
- RabbitMQ (events)
- Redis (caching) 