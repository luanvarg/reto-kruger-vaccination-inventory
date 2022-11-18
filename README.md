
# Reto Kruger - Inventario de vacunación de empleados

Consiste en una aplicación para llevar el registro del inventario del estado de vacunación de los empleados de la empresa.

## Tecnologías Utilizadas

* Java 8
* Maven
* PostGreSQL
* Spring Boot

## Base de datos
Se creó una instancia del servicio SQL en Google Cloud Platform con el motor de base de datos de PostgreSQL versión 13.

### Acceso y credenciales
Para conectarse a esta instancia es necesario utilizar los siguientes parámetros:
* Dirección IP pública: 35.239.143.78
* Nombre de conexión: reto-kruger:us-central1:reto-kruger
* Usuario: reto-kruger
* Contraseña: J1m@6*B2Lqnl

## Modelo de datos 
![Image text](https://github.com/luanvarg/reto-kruger-vaccination-inventory/blob/master/DataModel/DataModel.png)

## Spring Boot
Para la elaboración de este reto se utiliza el framework Spring boot con el lenguaje de programación java. 

En este proyecto se utilizaron 5 módulos que son:
* Entity
* Repository
* Service
* Controller
* Security

Donde se trabajan diferentes clases e interfaces que permite a partir del uso de dependencias establecer conexión con la base de datos y poder realizar un CRUD. Por otro lado, también se configura que se puedan recibir peticiones http y ser capaz de responderlas. Además se toma en consideración el apartado de seguridad que en este caso se utiliza Basic Auth.


## Instalación 
```
$ git clone https://github.com/luanvarg/reto-kruger-vaccination-inventory.git
```
Para utilizar esta aplicación se debe ejecutar en el IDE de preferencia y funcionará correctamente, en caso de presentar problemas con las dependencias se recomienda utilizar reload project de maven.





## API Reference

#### Register

```http
  POST localhost:8080/employee/register
```
##### Authorization Basic Auth
| Parameter | Type     | 
| :-------- | :------- | 
| `Username` | `<username>` | 
| `Password` | `<password>` | 

##### Request Headers
| Parameter | Type     | 
| :-------- | :------- | 
| `user_role` | `ADMIN` | 

##### Body
```JSON
{
    "legal_id": "1207063791",
    "name": "Luis",
    "last_name": "Vargas",
    "email": "luis422vargas@gmail.com"
}
```



#### Update Employee Info

```http
  PATCH localhost:8080/employee/updateInfo/{id}
```
##### Authorization Basic Auth
| Parameter | Type     | 
| :-------- | :------- | 
| `Username` | `<username>` | 
| `Password` | `<password>` | 

##### Request Headers
| Parameter | Type     | 
| :-------- | :------- | 
| `user_role` | `USER or ADMIN` | 

##### Body
```JSON
{
    "name": "Luis",
    "last_name": "Vargas",
    "email": "luis422vargas@gmail.com",
    "legal_id": "1207063791",
    "username": "Luis1207063791",
    "password": "kt(u^(*@VK(",
    "birthdate": "1999-04-22",
    "address": "Guayaquil, Ciudadela el Paraiso",
    "phone": "0996753027",
    "vaccination_status": false,
    "vaccine_type": null,
    "vaccination_date": null,
    "dose_number": null,
    "user_role": "USER"
}
```
#### Delete Employee

```http
  DEL localhost:8080/employee/delete/{id}
```
##### Authorization Basic Auth
| Parameter | Type     | 
| :-------- | :------- | 
| `Username` | `<username>` | 
| `Password` | `<password>` | 

##### Request Headers
| Parameter | Type     | 
| :-------- | :------- | 
| `user_role` | `ADMIN` | 

#### Get all the employees

```http
  GET localhost:8080/employee/all
```
##### Authorization Basic Auth
| Parameter | Type     | 
| :-------- | :------- | 
| `Username` | `<username>` | 
| `Password` | `<password>` | 

##### Request Headers
| Parameter | Type     | 
| :-------- | :------- | 
| `user_role` | `ADMIN` | 

#### Get Info by Id

```http
  GET localhost:8080/employee/myInfo/{id}
```
##### Authorization Basic Auth
| Parameter | Type     | 
| :-------- | :------- | 
| `Username` | `<username>` | 
| `Password` | `<password>` | 

##### Request Headers
| Parameter | Type     | 
| :-------- | :------- | 
| `user_role` | `USER` | 

Se debe tomar en cuenta que se implementó la documentación de la API con Swagger en el proyecto.
Para acceder a esta se debe ejecutar el proyecto y utilizar localhost:8080/swagger-ui/index.html
* username: kruger
* password: $4EJFzjbZ032

## Authors

- [@Luis Vargas](https://www.github.com/luanvarg)


## Documentación

* [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/)
* [Installing Spring Boot](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/getting-started.html#getting-started.installing)
