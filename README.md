# federico-rivarola-pf

# Spring Boot App model for Java 11

Primero se debe configurar el Run Configuration las siguientes variables de entorno

SCOPE=LOCAL;APPLICATION=federico-rivarola-pf;

## Usage

#1. create user

Crear un usuario para poder utilizar los servicios

Endpoint:
POST http://localhost:8080/api/v1/user/create

Body request ejemplo:

{
"user": "user",
"password": "1234",
"idSubsidiary": "0001"
}

#2. login
Una vez creado el usuario y asignado a una subsidiary debe loguearse y obtener el token.
Este es necesario en todos los endpoints que se quieran consumir.

POST http://localhost:8080/api/v1/user/login
{
"username": "frivarola",
"pwd": "1234"
}
Devolvera el Bearer + token, este debe ser ingresado en el header de Authorization para poder consumir los endpoints a continuacion

#3 - Requerimiento 2
Estos endpoints permiten obtener el registro de las partes que existen en el stock de la subsidiary. Las subsidiaries solo pueden acceder a su respectivo stock.

Ejemplos
GET http://localhost:8080/api/v1/parts/list?querytype=V&date=2020-01-01
GET http://localhost:8080/api/v1/parts/list?querytype=C
GET http://localhost:8080/api/v1/parts/list?querytype=V&date=2020-01-01&order=1

#4 - Requerimiento 3
Permiten obtener ordenes existenes segun numero de Dealer. Las subsidiaries solo tienen permiso para acceder a sus dealers, no pueden acceder a la info de dealers de otros subsidiary.

Por ejemplo, el usuario que creamos previamente pertenece a la subsidiary 0001 (argentina), y sus dealers son el 0001 y el 0006
GET http://localhost:8080/api/v1/parts/orders?dealerNumber=0001
GET http://localhost:8080/api/v1/parts/orders?dealerNumber=0006
si hacemos
GET http://localhost:8080/api/v1/parts/orders?dealerNumber=0002 -> falla

#5 - Requerimiento 4 
Permite agregar un nuevo item al stock de la subsidiary a la que corresponde al usuario

POST http://localhost:8080/api/v1/parts/
Body request ejemplo
{
"idPart": "AA10",
"quantity": 100
}

#6 - Requerimiento 5
Permite a la casa del pais crear una nueva orden para un dealer en particular
POST http://localhost:8080/api/v1/parts/orders

body request

#7 - Requerimiento 6
Se implemento la seguridad con JWToken, creacion de usuario y manejo de sesion de usuario, la sesion de usuario permite conocer a que subsidiaria pertenece el usuario
Y gracias a esto no debemos pedirle el id de la subsidiaria en todas las llamads.
Ademas de facilitar el manejo de los roles 


