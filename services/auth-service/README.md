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
- Docker (optional, for containerization)

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
