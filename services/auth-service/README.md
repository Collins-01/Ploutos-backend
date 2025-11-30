# Ploutos Auth Service

This is the Authentication Service for the Ploutos application. It handles user authentication, registration, and token management (JWT).

## Project Structure

The project follows a standard Spring Boot architecture:

- **config**: Security and application configuration.
- **controller**: REST controllers for handling HTTP requests.
- **dto**: Data Transfer Objects for API requests and responses.
- **model**: Domain entities.
- **repository**: Data access layer.
- **service**: Business logic.

## Running the Application

You can use the included `Makefile` to run common tasks.

### Prerequisites

- Java 17
- Maven
- Docker & Docker Compose (for database)

### Database Setup

The auth service requires a PostgreSQL database. Use Docker Compose to start it:

```bash
# Start PostgreSQL, Redis, and pgAdmin
docker-compose up -d

# Verify services are running
docker-compose ps
```

**Database Services:**
- **PostgreSQL**: `localhost:5432` (Database: `ploutos_auth`)
- **Redis**: `localhost:6379` (Caching)
- **pgAdmin**: `http://localhost:5050` (Database UI)

For detailed database setup and management, see [DATABASE.md](DATABASE.md).

### Commands

| Command | Description |
|---------|-------------|
| `make run` | Starts the application locally using Maven. |
| `make clean` | Cleans build artifacts. |
| `make docker-build` | Builds the Docker image. |
| `make docker-run` | Runs the application in a Docker container. |
| `make docker-stop` | Stops and removes the Docker container. |
| `make docker-logs` | Shows logs from the running Docker container. |
| `make docker-shell` | Opens a shell inside the Docker container. |

### Manual Run

If you prefer not to use `make`, you can run the application using Maven directly:

```bash
mvn spring-boot:run
```

The application will start on port **8081**.
