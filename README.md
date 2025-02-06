Project Description

This is a Spring Boot-based Dating Application that allows users to register and find matches based on gender, age, and shared interests. 
The application uses a RESTful API with CRUD operations and filtering logic to provide the best possible matches.

Technologies Used
  Java
  Spring Boot
  Spring Data JPA
  Hibernate
  MySQL (or any preferred relational database)
  Thymeleaf (for UI rendering)
  Lombok (to reduce boilerplate code)
  Jakarta Persistence API
  
Working of the Project
  
 1. User Registration:
    . Users can register via the /register endpoint.
    . The entered user details are saved into the database.

2. Filtering Users for Matches:
   . Users provide preferences such as age, gender, and interests.
   . The application retrieves all users and applies filtering rules:
     . Excludes users of the same gender.
     . Sorts based on age proximity.
     . Matches based on shared interests.
  . The top N results are displayed based on user input.

3. Session Management:
   . The system manages session attributes to provide user feedback.
   . Success and error messages are stored in the session and removed once displayed.

4. Database Management:
   . Spring Data JPA is used to interact with the database.
   . Hibernate handles persistence and object mapping.

5. Frontend Interaction:
   . The application uses Thymeleaf for rendering UI components.
   . Users can interact with registration and match-finding pages.

Prerequisites
 . Ensure you have the following installed:
 . Java 20
 . Maven
 . MySQL or any compatible database
 . An IDE Spring Tool Suite 3

Database Configuration
  Update application.properties with my database
    spring.datasource.url=jdbc:mysql://localhost:3306/dating_app
    spring.datasource.username=root
    spring.datasource.password=varshith@1904
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

Access the Application
  . open google chrome and type http://localhost:8282/
  . Use the following endpoints:
    . /register - Register a new user
    . /match_user Find matches
    . /users - Get all users

