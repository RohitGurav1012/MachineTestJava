## MachineTestJava

Below Requiremnts are implemnted in Machine Test
This project demonstrates **CRUD operations** for Category and Product entities with a **One-to-Many relationship**, including **server-side pagination**.

## Features
-  Spring Boot with Maven
-  Category & Product CRUD APIs
-  One-to-Many Mapping (Category → Products)
-  Server-side Pagination
-  Fetch Product details with respective Category
-  MySQL Integration

##  Project Structure
MachineTestJava/ ├── src/ │ ├── main/ │ │ ├── java/com/example/demo/ │ resources/ │ │ └── application.properties ├── pom.xml └── README.md

##  Technology
- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Setup Instructions

### 1.  Prerequisites

Make sure the following are installed:
- Java JDK 17 or higher
- MySQL Server
- Spring Tool Suite (STS) or Eclipse with Maven support

### 2.  Database Setup
Create a database named `testdb` in your MySQL:

CREATE DATABASE testdb;
3.  Clone or Download the Project
bash
git clone https://github.com/RohitGurav1012/MachineTestJava.git
OR

Download the ZIP from GitHub.

Extract it to a folder like Desktop/test.

4.  Import into STS/Eclipse
Open STS

Go to File → Import → Maven → Existing Maven Projects

Select the project folder (where pom.xml is located)

Click Finish

5.  Update application.properties
Located at: src/main/resources/application.properties
spring.application.name=MachineTestJava
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Update username/password as per your local MySQL setup.

Run the Project
Right-click on MachineTestJavaApplication.java

Select Run As → Spring Boot App

Spring Boot will start on http://localhost:8080

 API Overview
Method	Endpoint	Description
POST	/categories	Add a new category
GET	/categories	Get all categories
PUT	/categories/{id}	Update a category
DELETE	/categories/{id}	Delete a category
POST	/products	Add a new product
GET	/products	Get all products (paginated)
GET	/products/{id}	Get product by ID
DELETE	/products/{id}	Delete a product
Pagination parameters:
/products?page=0&size=5

 Notes
Ensure MySQL is running before launching the app.
Hibernate will auto-create/update tables using spring.jpa.hibernate.ddl-auto=update.



