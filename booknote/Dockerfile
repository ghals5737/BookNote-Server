# Stage 1: Build the application
FROM --platform=linux/amd64 gradle:7.5.1-jdk17 AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon --parallel --stacktrace  # Caches dependencies
COPY src ./src
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM --platform=linux/amd64 openjdk:18-jdk-alpine AS runtime
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/booknote.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/booknote.jar"]
