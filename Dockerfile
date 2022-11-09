FROM amazoncorretto:11-alpine

WORKDIR /usr/src/app

COPY target/*.jar app.jar
