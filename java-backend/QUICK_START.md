# Quick Start Guide - Java Backend

## Prerequisites

1. **Java 17 or higher**
   - Check installation: `java -version`
   - Download from: https://adoptium.net/

2. **Maven 3.6+**
   - Check installation: `mvn -version`
   - Download from: https://maven.apache.org/download.cgi

## Setup Instructions

### 1. Navigate to Java Backend Directory
```bash
cd java-backend
```

### 2. Build the Project
```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile the Java code
- Run tests (if any)
- Create the JAR file

### 3. Run the Application

**Option A: Using Maven**
```bash
mvn spring-boot:run
```

**Option B: Using the JAR file**
```bash
java -jar target/employee-productivity-tool-1.0.0.jar
```

### 4. Access the Application

- **Frontend**: http://localhost:5501
- **API Base**: http://localhost:5501/api

## Database

The SQLite database (`productivity_tool.db`) will be automatically created in the project root on first run. Sample data (employees and tasks) will be automatically inserted.

## Configuration

Edit `src/main/resources/application.properties` to change:
- Server port (default: 5501)
- Database location
- Logging levels

## Troubleshooting

### Port Already in Use
If port 5501 is already in use:
1. Change `server.port` in `application.properties`
2. Or stop the process using port 5501

### Database Issues
- Delete `productivity_tool.db` to reset the database
- The database will be recreated with sample data on next run

### Build Errors
- Ensure Java 17+ is installed
- Ensure Maven is properly configured
- Try: `mvn clean install -U` (force update dependencies)

## Development

### Hot Reload
Spring Boot DevTools is included for automatic restart during development.

### IDE Setup
1. Import as Maven project in your IDE
2. Wait for dependencies to download
3. Run `ProductivityApplication.java` as Java Application

## API Testing

Test the API using curl or Postman:

```bash
# Get all employees
curl http://localhost:5501/api/employees

# Get all tasks
curl http://localhost:5501/api/tasks

# Get dashboard stats
curl http://localhost:5501/api/analytics/dashboard
```

## Next Steps

- Review the API endpoints in `README.md`
- Customize business logic in `service/` package
- Add authentication/authorization for production
- Configure CORS for specific origins in production


