# Stage 1: Build the Spring Boot application using Gradle
FROM  --platform=linux/amd64 gradle:7.5.1-jdk18 AS build

WORKDIR /app/booknote
COPY ./booknote .
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test --no-daemon

# Stage 2: Run the application using JDK
FROM openjdk:18-jdk-alpine

WORKDIR /app
COPY --from=build /app/booknote/build/libs/booknote-0.0.1-SNAPSHOT.jar /app/booknote.jar

EXPOSE 8080
CMD ["java", "-jar", "/app/booknote.jar"]
