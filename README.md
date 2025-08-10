# Person Manager
Person API to manage Person class in memory

This is a sample application developed in Spring-Boot 3 and Java 17 for evaluation. The application consists of a 
RESTful API for managing in-memory person information, including CRUD operations and age and salary calculations in a 
hexagonal architecture.

## What is it for?

The application manages an in-memory "database" (a `HashMap`) of `Person` objects and offers the following API endpoints:

| HTTP     | Endpoint                                        | Description                                                       |
|:---------|:------------------------------------------------|:------------------------------------------------------------------|
| `GET`    | `/person`                                       | List of all people, sorted by name.                               |
| `GET`    | `/person/{id}`                                  | Finds a specific person by their ID.                              |
| `POST`   | `/person`                                       | New person. Generates an ID automatically if one is not provided. |
| `PUT`    | `/person/{id}`                                  | Updates all data for an existing person.                          |
| `PATCH`  | `/person/{id}`                                  | Updates one or more attributes of an existing person.             |
| `DELETE` | `/person/{id}`                                  | Removes a person from the record.                                 |
| `GET`    | `/person/{id}/age?output={days; months; years}` | Calculates the person's age in days, months, or years.            |
| `GET`    | `/person/{id}/salary?output={min; full}`        | Calculates the person's salary in R$(BR) or minimum wages.        |

## Requirements

To download and run this project, you'll need the following software installed:

- Java OpenJDK 17 JDK or higher: To compile and run the application.
- Maven: To manage dependencies and build the project.

Under linux environment Java can be installed:

Check Java version
```bash
 java --version
```
Update dependencies and install Java 17:
```bash
 sudo apt update
```
```bash
 sudo apt install openjdk-17-jdk
```
List installed versions and select `java-17-openjdk`
```bash
 sudo update-alternatives --config java
```
## How to Download and Run

Follow the steps below to download the project from GitHub and run it on your local machine.

### 1. Get project from remote repository

Download zip file and extract the project:

```bash
 https://github.com/christiancms/person-manager.git
```

### 2. Open project folder.

### 3. Run the Application
#### Using Maven

Run the `mvn spring-boot:run` command in the terminal. This will download the dependencies, compile the code, and start the Spring Boot embedded server.

```bash
mvn spring-boot:run
```

### 4. Starting the Application

After starting the application, it should run on the default port `8080`.

- **API Endpoint:** `http://localhost:8080/person`