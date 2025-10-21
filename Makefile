.PHONY: run build test clean install update-deps check-port

# Default target
help:
	@echo "Available commands:"
	@echo "  make run           - Run the application"
	@echo "  make build        - Build the application"
	@echo "  make test         - Run tests"
	@echo "  make clean        - Clean build files"
	@echo "  make install      - Install dependencies"
	@echo "  make update-deps  - Update project dependencies"
	@echo "  make check-port   - Check if port 8080 is in use"

# Run the application
run:
	@echo "Starting the application..."
	./mvnw spring-boot:run

# Build the application
build:
	@echo "Building the application..."
	./mvnw clean package

# Run tests
test:
	@echo "Running tests..."
	./mvnw test

# Clean build files
clean:
	@echo "Cleaning..."
	./mvnw clean

# Install dependencies
install:
	@echo "Installing dependencies..."
	./mvnw install

# Update project dependencies
update-deps:
	@echo "Updating dependencies..."
	./mvnw versions:update-properties versions:display-plugin-updates

# Check if port 8080 is in use
check-port:
	@echo "Checking if port 8080 is in use..."
	@lsof -i :8080 || echo "Port 8080 is available"

# Run with custom profile (usage: make run-profile PROFILE=dev)
run-profile:
	@echo "Running with profile: $(PROFILE)"
	./mvnw spring-boot:run -Dspring-boot.run.profiles=$(PROFILE)
