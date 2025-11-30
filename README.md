# 🏛️ Ploutos — The Personal Finance Tracker API

Inspired by the Greek god of wealth, **Ploutos** (Πλοῦτος) represents balance, control, and growth — helping users not just track where their money goes, but understand how to make it work better for them.

## 💡 Project Overview

**Ploutos** is a modern, intelligent personal finance tracker built with Spring Boot that empowers users to take control of their money through structured tracking, smart insights, and automated financial summaries.

This comprehensive budget and expense tracking system is built as a microservices-based monorepo, helping users manage their finances through intelligent tracking, categorization, and analysis of income and expenses.

## 🏗️ Monorepo Structure

This repository is organized as a monorepo containing multiple microservices:

```
Ploutos/
├── README.md                    # This file
├── services/                    # All microservices
│   └── auth-service/           # Authentication & authorization service
└── (future additions)
    ├── shared/                  # Shared libraries and utilities
    ├── infrastructure/          # Infrastructure as code (IaC)
    └── docs/                    # Additional documentation
```

## 🚀 Services

### Auth Service

The **Authentication Service** handles user authentication, registration, and JWT token management.

- **Technology**: Spring Boot (Java 17)
- **Port**: 8081
- **Documentation**: [services/auth-service/README.md](services/auth-service/README.md)

**Quick Start:**
```bash
cd services/auth-service
make run
```

### Future Services

The following services are planned for future development:
- **Budget Service**: Budget creation, tracking, and management
- **Expense Service**: Expense recording and categorization
- **Analytics Service**: Financial insights and reporting
- **Notification Service**: Alerts and reminders

## 🛠️ Getting Started

### Prerequisites

- **Java 17** (for Spring Boot services)
- **Maven** (for building Java services)
- **Docker** (optional, for containerization)
- **Git** (for version control)

### Running Services Locally

Each service can be run independently. Navigate to the service directory and use the provided `Makefile`:

```bash
# Example: Running the auth service
cd services/auth-service
make run
```

Alternatively, you can use Maven directly:

```bash
cd services/auth-service
mvn spring-boot:run
```

### Using Docker

Most services include Docker support. To build and run a service in Docker:

```bash
cd services/auth-service
make docker-build
make docker-run
```

## 📚 Development Workflow

### Adding a New Service

1. Create a new directory under `services/`
2. Initialize the service with your chosen framework
3. Add a `README.md` documenting the service
4. Update this main README to list the new service

### Common Commands

Each service typically supports these `make` commands:

| Command | Description |
|---------|-------------|
| `make run` | Run the service locally |
| `make clean` | Clean build artifacts |
| `make docker-build` | Build Docker image |
| `make docker-run` | Run in Docker container |
| `make docker-stop` | Stop Docker container |
| `make docker-logs` | View container logs |

## 🤝 Contributing

This is a monorepo project. When contributing:

1. Keep services independent and loosely coupled
2. Document all APIs and interfaces
3. Follow the established directory structure
4. Update relevant README files
5. Ensure all tests pass before committing

## 📝 License

[Add your license information here]

## 📧 Contact

[Add your contact information here]
