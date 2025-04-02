# Project: Social media App API

## Background 

This project serves as the backend API for a social media application. The application focuses on managing user accounts and the messages they submit to the platform. The backend is responsible for delivering the necessary data to display this information and processing actions like user registration, login, message creation, update, and deletion.

## Features

- **User Account Management**: Register, login, and authenticate users.
- **Message Management**: Create, update, and delete messages.
- **User Message Retrieval**: View all messages posted by a particular user or view all messages on the platform.
- **Data Persistence**: Store and retrieve user data and messages from a relational database.

The project follows a **three-layer architecture**, consisting of:

- **Controller Layer**: Handles HTTP requests and routes them to appropriate service methods.
- **Service Layer**: Contains the core logic for processing data and interacting with the DAO layer.
- **Data Access Object Layer**: Manages data persistence, interacting with the database to fetch or modify data.

## Technologies Used

**Language:**  
- Java
- SQL

**Frameworks & Libraries:**  
- Javalin 
- Jackson 

**Database:**  
- H2
- JDBC

**Testing & Project Management:**  
- JUnit
- Maven
