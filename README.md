## To-Do List Spring Boot + VueJS

This project is a Full-Stack to-do list application built with Spring Boot, Docker, PostgreSQL on Docker, VueJS, and TailwindCSS.

### Prerequisites

* Java 17 or higher
* Docker
* Node Package Manager

### Preview
https://github.com/canyouhearthemusic/todo-list/assets/96036674/b0079852-08d5-4086-a4c6-e9c464ba0c98


### Project Structure

The project is structured as follows:
```
├── todo/
│   ├── pom.xml
│   ├── src/main/java/
│   │   ├── com/todolist/todo/
│   │   │   ├── controllers/
│   │   │   │   ├── TodoController.java
│   │   │   │   └── ...
│   │   │   ├── models/
│   │   │   │   ├── Todo.java
│   │   │   │   └── ...
│   │   │   ├── repositories/
│   │   │   │   ├── TodoRepository.java
│   │   │   │   └── ...
│   │   │   └── services/
│   │   │       ├── TodoService.java
│   │   │       └── ...
│   │   │   └── configs/
│   │   │       ├── CorsConfig.java
│   │   │       └── ...
│   │   │   └── dto/
│   │   │       ├── TodoDTO.java
│   │   │       └── ...
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── vue/
│   │       │   └── ...
└── docker-compose.yml
```



### Getting Started

To run the application, follow these steps:

1. Clone the repository:


``` git clone https://github.com/canyouhearthemusic/todo-list.git ```


2. Navigate to the project root directory:


``` cd todo ```


3. Build and run the Docker containers:


``` docker-compose up -d ```


4. Download all modules listed as dependencies in package.json


``` npm install ```


5. Once the containers are up and running, you can access the application by running this command.


``` npm run dev ```
