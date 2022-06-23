#!/bin/bash
touch .env.local
docker-compose up --force-recreate --build -d --remove-orphans

docker-compose exec bhp-backend-java mvn clean
docker-compose exec bhp-backend-java mvn package
#docker-compose exec bhp-backend-java mvn -Dmaven.test.skip=true package
docker-compose exec bhp-backend-java java -jar target/api.jar
