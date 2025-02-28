﻿# User Service

The **User Service** is a key component of our microservices architecture, responsible for managing user-related operations, such as registration, authentication, and user data retrieval. This service interacts with a secure database and integrates with other microservices to enable seamless user management and authentication across the application.

## Key Features

- **User Registration:** Allows new users to register by providing an email and password.
- **Authentication:** Supports secure login with JWT-based authentication.
- **User Management:** Enables retrieval of user details and admin-level operations like listing all users and deleting accounts.
- **Security:** Includes endpoints to test user authorization and access control.

## Technologies Used

- **Spring Boot**: Core framework for building the service.
- **Spring Security**: Provides authentication and authorization capabilities.
- **Java Persistence API (JPA)**: For database interactions.
- **PostgreSQL**: Backend database for user data storage.
- **Auth0 (JWT)**: For secure user authentication.
- **RESTful API Design**: Provides a clean and structured API interface.
- **Spring Cloud Eureka Client**
- **Swagger**: For API documentation and testing.

---

## Endpoints

### User-Specific Endpoints

#### `GET /users/user`
**Description:** Retrieves user details by their unique ID.

**Request Parameters:**
- `id` (Long) - The unique ID of the user.

**Response:**
```json
{
  "id": 1,
  "email": "example@example.com",
  "roles": ["USER"]
}
```

#### `POST /users/register`
**Description:** Registers a new user.

**Request Body:**
```json
{
  "email": "example@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "message": "User registered successfully"
}
```

#### `GET /users/secured`
**Description:** Verifies if a user is authorized and returns their ID and email.

**Response:**
```
secured ID: 1 example@example.com
```

### Admin-Specific Endpoints

#### `GET /admin/all`
**Description:** Retrieves a list of all users (Admin only).

**Response:**
```json
[
  {
    "id": 1,
    "email": "user1@example.com",
    "roles": ["USER"]
  },
  {
    "id": 2,
    "email": "admin@example.com",
    "roles": ["ADMIN"]
  }
]
```

#### `DELETE /admin/delete`
**Description:** Deletes a user by their unique ID (Admin only).

**Request Parameters:**
- `id` (Long) - The unique ID of the user to delete.

**Response:**
```json
{
  "message": "User deleted successfully"
}
```
#### `PUT /admin/updateRole`
**Description:** Updates user role by user id.

**Request Parameters:**
- `userId` (Long) - The unique ID of the user to update role.
- `role` (String) - The role to update usually “ROLE_ADMIN”.
**Response:**
```json
{
  "message": "User role successfully updated"
}
```

### Authentication Endpoints

#### `POST /auth/login`
**Description:** Authenticates a user and returns a JWT token.

**Request Body:**
```json
{
  "email": "example@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "accestoken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
}
```

---

## Database Schema
The users records are stored in the `users` table with the following structure:

|  Column  |          Type          | Collation | Nullable |             Default
|----------|------------------------|-----------|----------|----------------------------------
| id       | bigint                 |           | not null | generated by default as identity
| email    | character varying(255) |           |          |
| password | character varying(255) |           |          |
| role     | character varying(255) |           |          |
---

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Build and run the application using Maven or your preferred IDE.
4. The service will be available at `http://localhost:8081`.

---

## Notes

- Ensure the database is properly configured before running the service.
- Only authorized users can access secured endpoints.
- Admin-level operations require admin privileges.

---

## Future Enhancements

- Add support for user profile updates.
- Implement email verification for registration.
- Introduce rate limiting for login attempts.
