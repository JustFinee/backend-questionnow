# QuestionNow
This application allows to create, manage, solve and share questionnaires. This repository contais all buisness logic and operations related with backend topics, 
for example with data base. This application was created for need of my final project on college.  

Link to frontend side:  
https://github.com/JustFinee/frontend-questionnow

## Main Technologies
Project was created with:
* Java 8
* Spring Boot 2.3.5 RELEASE

## Database
In this app I have taken the approach of NOT using real database. Instead application runs with H2Database (in-memory databse). All object-relation mappings are provided
by Hibernate.

## Security
JWT ( JSON Web Token ) authorization with token is used for security layer.

## Setup
Project uses maven as build automation tool:  
$ cd backend-questionnow  
$ mvn clean install  
$mvn spring-boot:run
