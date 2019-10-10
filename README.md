# Generation KUP Final Project

Final project for Generation KUP formation regarding Java 8 and Microservice Architecture developed under Spring-Boot framework and Dev-Ops philosophy.

## Project Explanation

The project consists on creating a Blog REST-API in which any registered user on the APP could send a new Article and even non-registered user can write new Comments on articles.

Comments must be checked on a secondary API which will return a 400 HTTP Status in case of a swearing word found inside the comment.

Also it has to be published in Docker Hub and easily deployed using Docker-Compose commands or Kubernetes Minikube.

In addition, Unit tests and Integration tests were added for basic functionality. Not including the testing for User creation.

## Instructions

* While using Docker-Compose tool, only need to use command ``docker-compose build`` and afterwards ``docker-compose up`` on root directory. The application will be exposed on **localhost** on port **8443**.

* While using Minikube tool from Kubernetes, run command ``kubectl create -f kubernetes/db.yml && kubectl create -f kubernetes/swearing.yml && kubectl create -f kubernetes/blog.yml`` on root directory.

Afterwards, proceed to see which IP and PORT are used to expose the service using ``minikube service blog``, by default the PORT will be *30000*.

## CURL Examples

Examples of API usage with cURL. Please remind that using Minikube as a deploy tool would result on the generation a different **IP** and **PORT**.

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
  -H 'Authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NzA2OTI1ODcsImlzcyI6IkJsb2cgTWljcm9zZXJ2aWNlIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1NzE1NTY1ODd9.wdSIlwtud6dcD3qxFtiu4k2vodkizPw2K_RrUz8BOW9cFwuJ5SF3kmASZ3dghAJkz-niFWReMalvHFtEj9iNzQ' \
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