# Makefile for Ploutos Auth Service

.PHONY: help run clean docker-build docker-run docker-stop docker-logs docker-shell

# Default target
.DEFAULT_GOAL := help

# Variables
PROJECT_NAME = auth-service
JAR_FILE = target/auth-service-1.0.0.jar
DOCKER_IMAGE = ploutos/auth-service
DOCKER_CONTAINER = ploutos-auth-service
DB_NAME = ploutosdb
DB_USER = postgres
DB_PASSWORD = password

# Colors for output
GREEN = \033[0;32m
BLUE = \033[0;34m
YELLOW = \033[1;33m
NC = \033[0m # No Color

help: ## Show this help message
	@echo "$(BLUE)Ploutos Auth Service - Available Commands:$(NC)"
	@echo ""
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  $(GREEN)%-15s$(NC) %s\n", $$1, $$2}'

run: ## Run the application
	@echo "$(BLUE)Starting Ploutos Auth Service...$(NC)"
	mvn spring-boot:run

clean: ## Clean build artifacts
	@echo "$(BLUE)Cleaning build artifacts...$(NC)"
	mvn clean
	@rm -rf target/

# Docker commands
docker-build: ## Build Docker image
	@echo "$(BLUE)Building Docker image...$(NC)"
	mvn clean package -DskipTests
	docker build -t $(DOCKER_IMAGE):latest .

docker-run: ## Run Docker container
	@echo "$(BLUE)Starting Docker container...$(NC)"
	docker run -d \
		--name $(DOCKER_CONTAINER) \
		-p 8081:8081 \
		-e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/$(DB_NAME) \
		-e SPRING_DATASOURCE_USERNAME=$(DB_USER) \
		-e SPRING_DATASOURCE_PASSWORD=$(DB_PASSWORD) \
		$(DOCKER_IMAGE):latest
	@echo "$(GREEN)Container started!$(NC)"

docker-stop: ## Stop Docker container
	@echo "$(BLUE)Stopping Docker container...$(NC)"
	docker stop $(DOCKER_CONTAINER) || true
	docker rm $(DOCKER_CONTAINER) || true

docker-logs: ## Show Docker container logs
	docker logs -f $(DOCKER_CONTAINER)

docker-shell: ## Open shell in Docker container
	docker exec -it $(DOCKER_CONTAINER) /bin/bash