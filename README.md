# Generation KUP Final Project

Final project for Generation KUP formation regarding Java 8 and Microservice Architecture developed under Spring-Boot framework and Dev-Ops philosophy.

## Project Explanation

The project consists on creating a Blog REST-API in which any registered user on the APP could send a new Article and even non-registered user can write new Comments on articles.

Comments must be checked on a secondary API 

Also it has to be published in Docker Hub and easily deployed using Docker-Compose commands or Kubernetes Minikube.


## Instructions

* While using Docker-Compose tool, only need to use command ``docker-compose build`` and afterwards ``docker-compose up`` on directory. The application will be exposed on **localhost** on port **8443**.

* While using Minikube tool from Kubernetes, run commands ``jajajaja``

## CURL Examples
* Example JWT generation for user already in DB **admin**

```
curl -k -X POST \
  https://localhost:8443/login \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 49' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8443' \
  -H 'cache-control: no-cache' \
  -d '{
	"username": "admin",
	"password": "password"
}'
```
* Example **POST** for generation Article with logged User
```
curl -k -X POST \
  https://localhost:8443/articles \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NzA0NDMzOTQsImlzcyI6IkJsb2cgTWljcm9zZXJ2aWNlIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1NzEzMDczOTR9.RxwcfAEs621inJjkhnxJx4QNeUAyHDqI0c2N1aQV8pinEMKlkxzxcs85ktDZq1Ia1s0r9OU0gx4zpogEpdvEAQ' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 103' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8443' \
  -H 'cache-control: no-cache' \
  -d '{
	"authorName": "Test",
	"authorNick": "Test",
	"title": "Test",
	"summary": "Test",
	"text": "Test"
}'
```
* Example **POST** for generation Comments without logged User
```
curl -k -X POST \
  https://localhost:8443/articles/1/comment \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 87' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8443' \
  -H 'cache-control: no-cache' \
  -d '{
    "author": "Yo mismo",
    "message": "El tigre comía trigo en el trigal"
}'
```
* Example **POST** for generation Comments with swearing and not getting published
```
curl -k -X POST \
  https://localhost:8443/articles/1/comment \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 87' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8443' \
  -H 'cache-control: no-cache' \
  -d '{
    "author": "Yo mismo",
    "message": "El tigre comía lechuguino en el trigal"
}'
```
* Example **POST** without logged user and Return of Forbidden
```
curl -k -X POST \
  https://localhost:8443/articles \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 103' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8443' \
  -H 'cache-control: no-cache' \
  -d '{
	"authorName": "Test",
	"authorNick": "Test",
	"title": "Test",
	"summary": "Test",
	"text": "Test"
}'
```
* Example **GET** for receiving all generated Articles in DB
```
curl -k -X GET \
  https://localhost:8443/articles \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```
* Example **GET** for receiving a specific Article in DB
```
curl -k -X GET \
  https://localhost:8443/articles/1 \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```