# Hospital Management System

A terminal-based hospital management application built with Java and Spring Boot. It supports basic management of patients, doctors, and appointments, and uses a custom doubly linked list and JSON files for data storage.

This project was developed as an object-oriented programming portfolio project to practice Java, collections, persistence, and common design patterns.

## Features

- Register and discharge patients and doctors
- Search for patients and doctors by name or ID
- Create and view appointments, preventing a doctor from being booked twice on the same date
- Sort patient and doctor lists by name or ID
- Persist doctors, patients, and appointments as JSON
- Validate that person IDs are unique across patients and doctors

## Design and implementation

- **Factory pattern:** `PersonFactory` creates patient and doctor objects.
- **Strategy pattern:** interchangeable strategies sort people by name or ID.
- **Decorator pattern:** `InsuranceDecorator` extends a person's displayed profile.
- **Custom collection:** a generic doubly linked list stores the in-memory records.
- **Spring Boot:** wires the application's services and starts the command-line runner.

## Tech stack

- Java 25
- Spring Boot 4
- Maven Wrapper
- Jackson for JSON serialization
- JUnit 5 and Spring Boot Test

## Requirements

- JDK 25
- Internet access on the first build so the Maven Wrapper can download Maven and dependencies

## Run

From the repository root, start the interactive application:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

Use the numbered menu to manage records. Choose `0` to save the current data and exit.

## Run tests

On Windows:

```powershell
.\mvnw.cmd test
```

On macOS or Linux:

```bash
./mvnw test
```

Build the executable Spring Boot JAR with:

```powershell
.\mvnw.cmd package
```

## Data files

The application reads and writes its JSON files in the `data/` directory relative to the working directory:

- `data/doctors.json`
- `data/patients.json`
- `data/appointments.json`

The repository includes empty JSON arrays as starter data. Records are loaded when the application starts and saved when you exit through the menu. Use fictional sample records only; do not put real personal or medical information in the repository.

## Project layout

```text
.
├── data/                         # JSON data files
├── src/
│   ├── main/java/LabProject/     # Application, models, services, and patterns
│   ├── main/resources/           # Application configuration
│   └── test/java/LabProject/     # Automated tests
├── .mvn/                         # Maven Wrapper configuration
├── mvnw
├── mvnw.cmd
└── pom.xml
```
