# Enhanced E-Commerce REST API 🛒

A robust, scalable e-commerce backend API built with **Spring Boot**, featuring product management, order processing, user authentication, analytics, and advanced security.

## Features ✨
- **Product Management**: CRUD operations, inventory tracking, dynamic pricing.
- **User Management**: Registration, JWT/OAuth2 authentication, role-based access (ADMIN, CUSTOMER).
- **Order Workflow**: Order lifecycle tracking, coupon system, audit logs.
- **Analytics**: Sales reports, revenue by category, top-selling products.
- **Security**: Rate limiting, encryption, RBAC.
- **Advanced Integrations**: Redis caching, AWS S3 file storage, Kafka/RabbitMQ for event-driven tasks.

## Tech Stack 🛠️
- **Backend**: Spring Boot, Spring Security, JPA/Hibernate.
- **Database**: PostgreSQL with Flyway migrations.
- **Auth**: JWT, OAuth2 (Google/GitHub).
- **Tools**: Swagger/OpenAPI,github.

## API Endpoints 🌐
| Feature          | Endpoints                                                                 |
|------------------|---------------------------------------------------------------------------|
| **Products**     | `GET /products`, `POST /products`, `PUT /products/{id}`, `DELETE /products/{id}` |
| **Users**        | `POST /users/register`, `POST /users/login`, `GET /users/{id}`           |
| **Orders**       | `POST /orders`, `GET /orders`, `PUT /orders/{id}/status`                 |
| **Analytics**    | `GET /analytics/sales`, `GET /analytics/users`, `GET /analytics/orders`  |
| **Coupons**      | `POST /coupons`, `POST /orders/{id}/apply-coupon`                        |
