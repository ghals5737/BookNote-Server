# Stage 1: Build the Spring Boot application using Gradle
FROM openjdk:18-jdk-alpine as build

# Set the working directory
WORKDIR /app

# Copy all project files to the container
COPY gradlew .
COPY gradle gradle

# Grant execution permission to Gradle wrapper
RUN chmod +x ./gradlew
COPY . .

# Build the project, skipping tests
RUN ./gradlew build -x test

# Stage 2: Create the final image with the built JAR
FROM openjdk:18-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous build stage
COPY --from=build /app/build/libs/*.jar /app/booknote.jar

# Expose the port that Spring Boot runs on (8080 by default)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/booknote.jar"]
