# Employee Productivity Tool - Java Spring Boot Backend

This is the Java Spring Boot version of the Employee Productivity Tool backend.

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** - For database operations
- **SQLite** - File-based database
- **Maven** - Build and dependency management

## Project Structure

```
java-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/productivity/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST API controllers
│   │   │   ├── model/           # Entity classes
│   │   │   ├── repository/      # Data access interfaces
│   │   │   ├── service/         # Business logic
│   │   │   └── ProductivityApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── pom.xml
└── README.md
```

## API Endpoints

All endpoints are prefixed with `/api`:

### Employees
- `GET /api/employees` - Get all employees
- `POST /api/employees` - Create new employee
- `DELETE /api/employees/{id}` - Delete employee

### Tasks
- `GET /api/tasks` - Get all tasks (optional `?status=` filter)
- `POST /api/tasks` - Create new task
- `POST /api/tasks/{id}/assign` - Auto-assign task
- `PUT /api/tasks/{id}/status` - Update task status

### Analytics
- `GET /api/analytics/dashboard` - Get dashboard statistics
- `GET /api/analytics/trends` - Get productivity trends

### Recommendations
- `GET /api/recommendations/{employeeId}` - Get employee recommendations

## Building and Running

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build
```bash
cd java-backend
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

Or run the JAR:
```bash
java -jar target/employee-productivity-tool-1.0.0.jar
```

The server will start on port **5501** (configurable in `application.properties`).

## Database

The application uses SQLite with the database file `productivity_tool.db` in the project root. The database is automatically initialized with sample data on first run.

## Configuration

Edit `src/main/resources/application.properties` to configure:
- Server port
- Database connection
- Logging levels

## Notes

- The frontend (`index.html`) should be placed in `src/main/resources/static/` or the project root to be served by Spring Boot
- CORS is enabled for all origins (configure in `CorsConfig.java` for production)
- The database schema is managed by Hibernate (ddl-auto=update)

