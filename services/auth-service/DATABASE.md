# Database Setup Guide

This guide explains how to set up and manage the PostgreSQL database for the Ploutos Auth Service.

## Quick Start

### 1. Start the Database

```bash
# Start all services (PostgreSQL, Redis, pgAdmin)
docker-compose up -d

# Or start only PostgreSQL
docker-compose up -d postgres
```

### 2. Verify Database is Running

```bash
# Check container status
docker-compose ps

# View logs
docker-compose logs postgres
```

### 3. Run the Auth Service

```bash
# The service will automatically connect to the database
mvn spring-boot:run
```

## Services Included

### PostgreSQL Database
- **Container**: `ploutos-auth-db`
- **Port**: `5432`
- **Database**: `ploutos_auth`
- **Username**: `ploutos_user`
- **Password**: `ploutos_password`
- **JDBC URL**: `jdbc:postgresql://localhost:5432/ploutos_auth`

### Redis Cache (Optional)
- **Container**: `ploutos-auth-redis`
- **Port**: `6379`
- **Purpose**: Session caching, rate limiting, token blacklisting

### pgAdmin (Database Management UI)
- **Container**: `ploutos-pgadmin`
- **Port**: `5050`
- **URL**: http://localhost:5050
- **Email**: `admin@ploutos.local`
- **Password**: `admin`

## Common Commands

### Start Services
```bash
# Start all services
docker-compose up -d

# Start specific service
docker-compose up -d postgres
```

### Stop Services
```bash
# Stop all services
docker-compose down

# Stop and remove volumes (WARNING: deletes all data)
docker-compose down -v
```

### View Logs
```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f postgres
```

### Database Access

#### Using psql (Command Line)
```bash
# Connect to database
docker exec -it ploutos-auth-db psql -U ploutos_user -d ploutos_auth

# Common psql commands
\dt          # List all tables
\d users     # Describe users table
\q           # Quit
```

#### Using pgAdmin (Web UI)
1. Open http://localhost:5050
2. Login with credentials above
3. Add New Server:
   - **Name**: Ploutos Auth DB
   - **Host**: postgres (or host.docker.internal on Mac/Windows)
   - **Port**: 5432
   - **Database**: ploutos_auth
   - **Username**: ploutos_user
   - **Password**: ploutos_password

## Database Schema

The application uses JPA with `ddl-auto: update`, which means:
- Tables are automatically created on first run
- Schema updates are applied automatically
- No manual migrations needed for development

### Main Tables
- `users` - User accounts and authentication
- `roles` - User roles (ADMIN, USER)
- `user_roles` - Many-to-many relationship

## Environment Variables

Copy `.env.example` to `.env` and customize:

```bash
cp .env.example .env
```

Then edit `.env` with your preferred values.

## Production Considerations

For production deployment:

1. **Change default passwords** in `docker-compose.yml`
2. **Use environment variables** instead of hardcoded values
3. **Set up database backups**
4. **Use a strong JWT secret** (minimum 256 bits)
5. **Enable SSL/TLS** for database connections
6. **Set `ddl-auto: validate`** instead of `update`
7. **Use database migrations** (Flyway or Liquibase)

## Troubleshooting

### Database Connection Failed
```bash
# Check if PostgreSQL is running
docker-compose ps postgres

# Check logs for errors
docker-compose logs postgres

# Restart the database
docker-compose restart postgres
```

### Port Already in Use
```bash
# Check what's using port 5432
lsof -i :5432

# Change the port in docker-compose.yml
ports:
  - "5433:5432"  # Use 5433 instead

# Update application.yml accordingly
```

### Reset Database
```bash
# Stop and remove all data
docker-compose down -v

# Start fresh
docker-compose up -d
```

## Data Persistence

Database data is persisted in Docker volumes:
- `postgres_data` - PostgreSQL data
- `redis_data` - Redis data
- `pgadmin_data` - pgAdmin configuration

To backup data:
```bash
# Backup database
docker exec ploutos-auth-db pg_dump -U ploutos_user ploutos_auth > backup.sql

# Restore database
docker exec -i ploutos-auth-db psql -U ploutos_user -d ploutos_auth < backup.sql
```
