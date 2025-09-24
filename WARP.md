# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

ElevatorPitch is a Spring Boot application designed to help users create structured elevator pitches through a guided questionnaire system. The application collects user responses to predefined questions and generates personalized elevator pitch outlines.

## Architecture

### Backend Structure
- **Spring Boot 3.1.5** with **Java 17**
- **Maven** for dependency management
- **H2 in-memory database** for development/testing
- **Spring Data JPA** for data persistence
- **RESTful API** architecture

### Key Components
- **Entities** (`com.elevatorpitch.entity`): `Question` (survey questions with multiple choice options), `UserResponse` (user's session-based answers)
- **Repositories** (`com.elevatorpitch.repository`): JPA repositories for data access (`QuestionRepository`, `UserResponseRepository`)
- **Service Layer** (`com.elevatorpitch.service`): `ElevatorPitchService` handles business logic for questions, answers, and outline generation
- **REST Controllers** (`com.elevatorpitch.controller`): `ElevatorPitchController` provides API endpoints under `/api`
- **DTOs** (`com.elevatorpitch.dto`): `AnswerRequest` for incoming requests, `OutlineResponse` for generated outlines
- **Configuration** (`com.elevatorpitch.config`): `CorsConfig` for frontend integration, `DataInitializer` for sample questions

### Data Model
- Questions have ordered multiple-choice options stored as element collections
- User responses are session-based, allowing multiple users to use the application simultaneously
- Sessions are identified by string IDs, and responses maintain question order for outline generation

### API Endpoints
- `GET /api/questions` - Retrieve all questions
- `GET /api/questions/{id}` - Get specific question
- `POST /api/answers` - Submit user response
- `GET /api/outline/{sessionId}` - Generate elevator pitch outline
- `GET /api/responses/{sessionId}` - Get all responses for a session

## Development Commands

### Building and Running
```bash
# Navigate to backend directory (when code is merged from copilot branch)
cd backend

# Build the application
mvn clean compile

# Run the application
mvn spring-boot:run

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Package the application (creates JAR file)
mvn clean package

# Run the packaged JAR
java -jar target/elevator-pitch-backend-1.0.0.jar
```

### Testing
```bash
# Navigate to backend directory first
cd backend

# Run all tests
mvn test

# Run specific test class (example - adjust class name as needed)
mvn test -Dtest=ElevatorPitchServiceTest

# Run tests with coverage (requires jacoco plugin configuration)
mvn test jacoco:report

# Clean and run tests
mvn clean test
```

### Development Utilities
```bash
# Navigate to backend directory first
cd backend

# Clean compiled classes and target directory
mvn clean

# Compile only (no tests)
mvn compile

# Show dependency tree
mvn dependency:tree

# Check for dependency updates
mvn versions:display-dependency-updates

# Run application in debug mode
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

### Database Management
```bash
# Access H2 Console (when app is running)
# Navigate to: http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:mem:testdb
# Username: sa
# Password: password
```

### Development Workflow
The main branch contains minimal setup. The actual Spring Boot implementation exists in the copilot branch (`remotes/origin/copilot/fix-bf01caa6-a3f8-4aa4-960c-2dd9f964b022`). To work with the full codebase:

```bash
# Merge or checkout the copilot branch to access the Spring Boot code
git merge origin/copilot/fix-bf01caa6-a3f8-4aa4-960c-2dd9f964b022
```

## Project Configuration

### Key Configuration Files
- `backend/pom.xml` - Maven dependencies and build configuration
- `backend/src/main/resources/application.properties` - Spring Boot configuration
  - Server runs on port 8080
  - CORS enabled for localhost:4200 (Angular frontend)
  - H2 database with DDL auto-creation

### Dependencies
- Spring Boot Starter Web, Data JPA, Validation
- H2 Database (runtime)
- Spring Boot Test (test scope)

### Data Initialization
The application automatically populates sample questions on startup via `DataInitializer`:
1. Professional background options
2. Opportunity type seeking
3. Primary strengths
4. Unique qualities
5. Main goals

Each question has 5 multiple-choice options that users can select from to build their elevator pitch.

## Integration Notes
- Frontend integration expected on `http://localhost:4200` (likely Angular)
- CORS is configured to allow cross-origin requests from the frontend
- Session-based responses allow multiple concurrent users
- RESTful API follows standard HTTP conventions with proper status codes