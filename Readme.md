# QNP Assignment

This application is designed to be run using Docker.

## Running the Application

To run the application without any problem, please use Docker with the following commands:

```bash
docker build -t qnp-assignment:1.0.0 .
docker run -p 8080:8080 qnp-assignment:1.0.0

API Endpoints
JWT Token Generation
To generate the JWT token, hit this API:

GET /generate-token

Contacts API
The Contacts API supports the following operations:

GET /contacts/{id}  # Find contact by id

POST /contacts  # Create contact 
{
    "name": "abc",
    "email": "phone",
    "address": "home",
    "phone": "+6281808194455"
}

PUT /contacts/{id}  # Update contact 
{
    "name": "abc",
    "email": "phone",
    "address": "home",
    "phone": "+6281808194455"
}

DELETE /contacts/{id}  # Delete contact 

Concurrent API
The Concurrent API supports the following operation:

GET /concurrent-data/fetch

Please replace `{id}` with the actual ID of the contact when using the API.

Sample curl curl --location --request GET 'http://localhost:8080/concurrent-data/fetch' \
--header 'Authorization: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiZXhwIjoxNzA5MDU0MzIwfQ.0NmelrTPVEQ9duLf6X8PKy_sZqUPquk2LY-A7MLqnj4'