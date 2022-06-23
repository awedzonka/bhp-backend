#!/bin/bash
docker-compose exec bhp-backend-java mvn clean
docker-compose exec bhp-backend-java mvn package
docker-compose exec bhp-backend-java java -jar target/api.jar
