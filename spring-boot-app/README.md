# Spring Boot Application - Migrated from Pro*C

## Overview
This application was migrated from an Oracle Pro*C embedded SQL application to a Java 21 Spring Boot 3.x REST API using Spring Data JPA.

## Prerequisites
- Java 21
- Maven 3.8+
- Oracle Database (or H2 for local testing)

## Configuration

Database connection is configured via environment variables:

| Variable | Description | Default |
|----------|-------------|---------|
| DB_URL | Oracle JDBC URL | jdbc:oracle:thin:@localhost:1521:ORCL |
| DB_USERNAME | Database username | (none) |
| DB_PASSWORD | Database password | (none) |
| SERVER_PORT | Application port | 8080 |

## Build

```bash
mvn clean package
```

## Run

```bash
java -jar target/source-0.0.1-SNAPSHOT.jar
```

For local testing with H2 in-memory database:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

## Run Tests

```bash
mvn test
```

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | /api/employees | Create a new employee |
| GET | /api/employees | List all employees |
| GET | /api/employees/{empno} | Get employee by empno |
| PUT | /api/employees/{empno} | Update employee salary |
| DELETE | /api/employees/{empno} | Delete employee |

### Request/Response Examples

**Create Employee:**
```json
POST /api/employees
{
  "empno": 1001,
  "ename": "JOHN",
  "sal": 5000.00
}
```

**Update Employee Salary:**
```json
PUT /api/employees/1001
{
  "sal": 6000.00
}
```
