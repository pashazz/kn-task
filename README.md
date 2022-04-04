# Build & run

``` sh

./gradlew clean build
docker-compose up -d 

```


# Outline

This is a microservices-based backend application. Unfortunately I haven't been able to find time to do a frontend but I may try to do it


## Microservices

This application consist of 3 microservices:

* API gateway
* Authentication system
* Cities API (+ DB)
* Discovery client (Netflix Eureka)

## Users

Users are configured in YAML, [[./auth/src/main/resources/application.yml]], section kndemo.auth.users

set canEdit to false to disable city editing

Authorization is done via passing a JWT token

## Requests
### Authorize as editor
```http request
POST http://localhost:8080/auth/signIn
Content-Type: application/json

{
  "login": "pasha",
  "password": "pasha"
}
```


### Authorize as non-editor
```http request
POST http://localhost:8080/auth/signIn
Content-Type: application/json

{
  "login": "mike",
  "password": "michael"
}
```


### validate a token
```http request
POST http://localhost:8080/auth/validateToken?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNoYSIsIkNBTl9FRElUIjoiVFJVRSIsImlhdCI6MTY0OTAzNTQxOCwiZXhwIjoxNjQ5MDM5MDE4fQ.YwKBuSCMl9VTkA6nFiSl2gvjCQPWsYTnRckZSEu6c8A
```


### list all - 1st page
```http request
GET http://localhost:8080/cities/v1/city/all
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNoYSIsIkNBTl9FRElUIjoiVFJVRSIsImlhdCI6MTY0OTAzNTQzMywiZXhwIjoxNjQ5MDM5MDMzfQ.XYos_R5IZz5a9bjXpk7WHFAC91kJarfWQp7-zc4ON90
```

### list all - paged
```http request
GET http://localhost:8080/cities/v1/city/all
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNoYSIsIkNBTl9FRElUIjoiVFJVRSIsImlhdCI6MTY0OTAzNTQzMywiZXhwIjoxNjQ5MDM5MDMzfQ.XYos_R5IZz5a9bjXpk7WHFAC91kJarfWQp7-zc4ON90
Content-Type: application/json

{
  "page": 2,
  "size": 200
}
```
### edit a city
```http request
PATCH http://localhost:8080/cities/v1/city
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNoYSIsIkNBTl9FRElUIjoiVFJVRSIsImlhdCI6MTY0OTAzNTQxOCwiZXhwIjoxNjQ5MDM5MDE4fQ.YwKBuSCMl9VTkA6nFiSl2gvjCQPWsYTnRckZSEu6c8A
Content-Type: application/json

{
 "id" : "d355972c-559c-4959-86fc-3a87ea68535a",
 "name" : "Osaka"
}
```


### Find by name
```http request
GET http://localhost:8080/cities/v1/city/filter
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNoYSIsIkNBTl9FRElUIjoiVFJVRSIsImlhdCI6MTY0OTAzNTQxOCwiZXhwIjoxNjQ5MDM5MDE4fQ.YwKBuSCMl9VTkA6nFiSl2gvjCQPWsYTnRckZSEu6c8A
Content-Type: application/json

{
 "query" : "Os"
}
```
