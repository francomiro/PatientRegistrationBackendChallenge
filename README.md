# Patient Registration Backend Challenge

## Descripción

Esta es una API creada para el challenge de Light-it, se encarga de la gestion de registro de pacientes. Permite registrar pacientes, almacenar sus datos y documentos, y enviar notificaciones por correo electrónico. 
La aplicación está desarrollada con **JAVA**  **Spring Boot** y utiliza **MySQL** como base de datos. 

## Requisitos

### Software Necesario

- **Docker**: Para crear y ejecutar los contenedores necesarios.
  
#### En caso de querer correr la API localmente y sin docker:
- **Java 17**
- **Maven**
- **MySQL**

### Dependencias

- **Spring Boot**
- **Hibernate**
- **Spring Data JPA**
- **Spring Mail**

## Instrucciones para el Despliegue con Docker

### 1. Clonar el repositorio

### 2. Editar configuracion a gusto
Antes de levantar los contenedores hay que configurar en el fichero **docker-compose.yml** la url y credenciales de Mailtrap para recibir los correos en su propia cuenta, en caso que no tengan, pueden dejar los valores que trae definidos que son los relacionados a mi cuenta 
              
      SPRING_MAIL_HOST
      SPRING_MAIL_PORT
      SPRING_MAIL_USERNAME
      SPRING_MAIL_PASSWORD
      
### 3. Iniciar los contenedores con Docker Compose


Levantar los contenedores (la base de datos y la API) ejecutando el siguiente comando:

    docker-compose up --build

La API quedara levantada en la siguiente URL: 

    http://localhost:8080

### 3. Prueba de la API
**Registrar un paciente**

- URL: /patients/
- Método: POST
- Descripción: Registra un nuevo paciente en el sistema.
- Body (JSON)

Para probarla el mejor metodo es a traves de Postman, aqui dejo el cURL para importar el request: 

    curl --location 'http://localhost:8080/patients/' \
    --form 'name="Franco Miro"' \
    --form 'email="franco.miro99@gmail.com"' \
    --form 'phone="1164519791"' \
    --form 'documentPhoto=@"/path/to/file"'

Una vez importado el request quedara pendiente agregar un fichero en el campo documentPhoto (no tiene que superar los 10mb). 

**Respuestas posibles**
- 200 OK: Si el paciente se registró correctamente.
- 400 Bad Request: Si falta algún dato obligatorio o no cumple el formato adecuado.



  