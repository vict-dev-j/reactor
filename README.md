# Overview
Reactor is a Spring Boot application that provides a RESTful API for managing user accounts. This project uses MongoDB for data storage and runs in Docker containers.

## Features
CRUD Operations: Create, Read, Update, and Delete user accounts.
Reactive Programming: Utilizes Spring WebFlux and Project Reactor for reactive programming.
MongoDB Integration: Stores user account data in MongoDB.
Dockerized Setup: Includes Docker support for easy deployment.

## Prerequisites
Java 17: The project is compatible with Java 17.


## Start the Application in docker:
docker-compose up --build

The application will be accessible at http://reactor:8080, and MongoDB will be available at mongodb://mongo:27017.


## API Endpoints for testing

```bash
curl -X POST http://reactor:8080/user-accounts/create -H "Content-Type: application/json" -d "{\"name\": \"N S\", \"email\": \"n.s@example.com\"}"
```

```bash
curl -X GET http://reactor:8080/user-accounts/all
```

```bash
curl -X GET http://reactor:8080/user-accounts/{id}
```

```bash
curl -X PUT http://reactor:8080/user-accounts/{id} -H "Content-Type: application/json" -d "{\"name\": \"NewName NewSurname\", \"email\": \"nn.ns@example.com\"}"
```

```bash
curl -X DELETE http://reactor:8080/user-accounts/{id}
```
