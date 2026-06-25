# Source - Spring Boot Application

Spring Boot 3.x application migrated from Oracle Pro*C. Provides a RESTful API for employee CRUD operations.

## Prerequisites

- Java 21
- Maven 3.9+
- Oracle database (or H2 for local testing)

## Configuration

Set the following environment variables for database connection:

| Variable | Description | Default |
|----------|-------------|---------|
| `DB_URL` | JDBC URL | `jdbc:oracle:thin:@localhost:1521:ORCL` |
| `DB_USERNAME` | Database username | (none) |
| `DB_PASSWORD` | Database password | (none) |
| `SERVER_PORT` | Application port | `8080` |
| `OTEL_EXPORTER_OTLP_ENDPOINT` | OpenTelemetry OTLP endpoint | `http://localhost:4318` |
| `OTEL_SERVICE_NAME` | OTel service name | `source` |

## Build

```bash
mvn clean package
```

## Run

```bash
java -jar target/source-1.0.0-SNAPSHOT.jar
```

### Run with H2 (local testing)

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
| POST | `/api/employees` | Create employee |
| GET | `/api/employees/{empno}` | Get employee by ID |
| GET | `/api/employees` | List all employees |
| PUT | `/api/employees/{empno}` | Update employee salary |
| DELETE | `/api/employees/{empno}` | Delete employee |

### Example: Create Employee

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"empno": 1001, "ename": "JOHN", "sal": 5000.00}'
```
