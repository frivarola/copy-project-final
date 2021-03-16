# federico-rivarola-pf

# Spring Boot App model for Java 11

We provide a basic model for JDK 11 / Spring based web applications.

Please address any questions and comments to [Fury Issue Tracker](https://github.com/mercadolibre/fury/issues).

## Usage

#1. create user
POST http://localhost:8080/api/v1/user/create

{
"user": "user",
"password": "1234",
"idSubsidiary": "0001"
}

#2. login
POST http://localhost:8080/api/v1/user/login
{
"username": "frivarola",
"pwd": "1234"
}