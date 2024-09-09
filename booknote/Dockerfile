# Step 1: Use a base image with OpenJDK 18
FROM openjdk:18-jdk-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Gradle wrapper files and the source code to the container
COPY . .

# Step 4: Grant execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Step 5: Build the project using the Gradle wrapper
RUN ./gradlew build -x test

# Step 6: Copy the built JAR file to run it
COPY build/libs/*.jar /app/booknote.jar

# Step 7: Expose the port (8080 is the default Spring Boot port)
EXPOSE 8080

# Step 8: Run the Spring Boot application
CMD ["java", "-jar", "/app/booknote.jar"]
