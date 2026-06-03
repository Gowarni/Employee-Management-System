# Employee Management System - Spring Boot REST API

A RESTful Employee Management System built using Spring Boot, Spring Data JPA, Hibernate, and MySQL.

## Features

- Create Employee
- Get Employee by ID
- Get All Employees
- Update Employee
- Delete Employee
- Get Employees by Department
- Pagination and Sorting
- Employee Statistics
- Department-wise Employee Count
- Email Duplicate Validation
- Global Exception Handling
- Swagger API Documentation
## Architecture

Controller Layer
↓
Service Layer
↓
Repository Layer
↓
MySQL Database

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Swagger/OpenAPI

## API Endpoints

### Employee APIs

| Method | Endpoint |
|----------|----------|
| POST | /employees |
| GET | /employees |
| GET | /employees/{id} |
| PUT | /employees/{id} |
| DELETE | /employees/{id} |
| GET | /employees/department/{department} |

### Pagination & Sorting

```http
GET /employees/paged?page=0&size=5&sortBy=salary&direction=asc
```

### Statistics

```http
GET /employees/stats
```

### Department Count

```http
GET /employees/department-count
```

## Swagger Documentation

After running the application:

```http
http://localhost:8080/swagger-ui/index.html
```

## Database Configuration

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=root
spring.datasource.password=your_password
```

## Run Project

```bash
mvn clean install
mvn spring-boot:run
```

## Author

**Gowarni Pullagura**