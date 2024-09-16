## Ride Booking App Backend

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Installation](#installation)
    - [Usage](#usage)
- [API Documentation](#api-documentation)

## Introduction
This is the backend system for a Ride Booking App, built using Spring Boot. The system allows passengers to book rides, drivers to offer rides, and admins to manage users. The backend handles user authentication, ride management, fare calculation, and real-time ride status tracking.  

The project is designed following RESTful API principles, and it connects with a relational spatial database to store data. Additionally, it implements security using JWT tokens and role-based access control.  

## Features
User Authentication and Authorization (Rider, Driver, Admin)  
Role-Based Access Control using JWT and Secured annotation  
Rider Registration  
Ride Management: Ride creation, ride acceptance, ride completion  
Fare Calculation strategies based on distance and time of requested ride  
Driver Matching: Find nearest drivers for passengers or the highest rated driver  
Admin Panel: Onboard a new driver  
Database Management: CRUD operations for users, rides, and vehicles  
Error Handling: Custom error responses  
Logging and Monitoring: Integrated with logging for debugging  

## Technologies Used
- Spring Boot (Core framework)  
- Spring Data JPA (Database access)  
- PostgreSQL with PostGIS(Relational database)  
- Spring Security (Authentication and Authorization)  
- JWT (JSON Web Tokens) (Stateless authentication)  
- Spring Web (REST API)  
- Hibernate (ORM)  
- Lombok (Boilerplate code reduction)  
- Swagger/OpenAPI (API documentation)  
- Maven (Build tool)  
- OSRM API  

**System Architecture**  

Rider: Requests rides, cancels rides, rates driver, view all his rides  
Driver: Accepts rides, cancels rides, starts rides, end rides, rates riders, view all his serviced rides.  
Admin: Onboards new drivers.  

The system follows a 3-layer architecture:  
Controller: Handles incoming HTTP requests.  
Service: Implements business logic.  
Repository: Interacts with the database using Spring Data JPA.

## Getting Started
**Prerequisites**

- **Java 17** or higher  
- **Maven** 3.6+
- **PostgreSQL with PostGIS**(or any other relational database supporting spatial features)  
- **Postman** or any API testing tool (optional)  
- **AWS** account  

## Installation
**Clone the repository:**

git clone https://github.com/enigma2797/uber-app.git  
cd uber-app

**Configure the database:**

Open the application.properties or application.yml file and update the database connection details.  

spring.datasource.url=jdbc:mysql://localhost:3306/{database_name}  
spring.datasource.username=yourusername  
spring.datasource.password=yourpassword  
spring.jpa.hibernate.ddl-auto=update  

## Usage

**Run the application using:**

mvn spring-boot:run

## API Documentation
The API documentation is available through Swagger at the following URL once the application is running:

http://localhost:8080/swagger-ui/index.html