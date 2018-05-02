# Ingeus User Api Application

This api exposes single endpoint (`/api/v1/users`) to save user details.
Application developed using following technologies:

- Spring Boot
- Maven
- Spring Data JPA
- H2(for database)

## Running
After downloading the source code run the command below to start the server:
    
    mvn spring-boot:run

The application will be running on port `8080` by default.
You can run `curl` command below to test if the application is running.

    ## Save User
    curl -X "POST" "http://localhost:8080/api/v1/users" \
         -H 'Content-Type: application/json' \
         -d $'{
      "firstName": "Mustafa",
      "lastName": "Kosker",
      "dateOfBirth": "13/11/1986",
      "password": "123456"
    }'

## Testing
You can run the command below to build the application and run integration tests accordingly.

    mvn clean install

## Notes
- CORS disabled for client application communication
