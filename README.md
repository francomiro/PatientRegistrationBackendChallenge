# Patient Registration Backend Challenge

## Description

This is an API created for the Light-it challenge. It handles patient registration management, allowing users to register patients, store their data and documents, and send email notifications.  
The application is developed using **JAVA**, **Spring Boot**, and utilizes **MySQL** as the database.

## Requirements

### Required Software

- **Docker**: To create and run the necessary containers.

#### If you want to run the API locally without Docker:
- **Java 17**
- **Maven**
- **MySQL**

### Dependencies

- **Spring Boot**
- **Hibernate**
- **Spring Data JPA**
- **Spring Mail**

## Deployment Instructions with Docker

### 1. Clone the Repository

### 2. Edit Configuration (Optional)
Before starting the containers, configure the **docker-compose.yml** file with your Mailtrap credentials to receive emails in your own account. If you don’t have an account, you can keep the default values, which correspond to my account:

    SPRING_MAIL_HOST
    SPRING_MAIL_PORT
    SPRING_MAIL_USERNAME
    SPRING_MAIL_PASSWORD

### 3. Start the Containers with Docker Compose

Start the containers (both the database and the API) by running the following command:

    docker-compose up --build

The API will be accessible at the following URL:

    http://localhost:8080

### 4. Test the API
**Register a Patient**

- **URL**: /patients/
- **Method**: POST
- **Description**: Registers a new patient in the system.
- **Body (JSON)**

To test the API, the recommended method is through Postman. Below is a cURL command you can import as a request:

    curl --location 'http://localhost:8080/patients/' \
    --form 'name="Juan Perez"' \
    --form 'email="Juanperez@gmail.com"' \
    --form 'phone="1164319791"' \
    --form 'documentPhoto=@"/path/to/file"'

Once imported into Postman, you will need to attach a file in the `documentPhoto` field (must not exceed 10 MB).

**Possible Responses**
- **201 CREATED**: If the patient was registered successfully.
- **400 Bad Request**: If any required field is missing or does not meet the required format.

### 5. View the Database
To view patient data stored in the database, you can either use Docker or configure a database client like DBeaver:  
- **Host:** localhost  
- **Port:** 3306 (mapped in Docker Compose).  
- **Database:** challenge_db (name of the database).  
- **Username:** root.  
- **Password:** admin.
  
![image](https://github.com/user-attachments/assets/e2447251-ebc5-4acf-8e7b-90265ba9fda0)

**Access from Docker:**
Run the following command in a new terminal:

    docker exec -it mysql mysql -u root -padmin

Once logged in, you can run SQL commands to view the tables. 

Here are some useful commands:

    SHOW DATABASES;

    USE challenge_db;

    SHOW TABLES;

    SELECT * FROM patient;

To view the attached documents:

    SELECT * FROM patient_document;

### 6. Stop Containers
To stop the containers after testing:

    docker-compose down
