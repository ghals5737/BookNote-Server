# Stage 1: Build the Spring Boot application using Gradle
FROM gradle:7.6-jdk-alpine as build

WORKDIR /app/booknote
COPY ./booknote .
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

FROM openjdk:18-jdk-alpine

WORKDIR /app
COPY --from=build /app/booknote/build/libs/*.jar /app/booknote.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/booknote.jar"]
