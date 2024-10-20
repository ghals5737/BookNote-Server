# Stage 1: Build the Spring Boot application using Gradle
FROM --platform=linux/amd64 gradle:7.5.1-jdk18 AS build

ARG DB_URL
ARG DB_USER_NAME
ARG DB_PASS_WORD
ARG REDIS_HOST
ARG REDIS_PORT
ARG KAFKA_HOST
ARG NAVER_CLIENT_ID
ARG NAVER_CLIENT_SECRET

WORKDIR /app/booknote
COPY . .

# These environment variables are used during the build phase
ENV DB_URL=${DB_URL}
ENV DB_USER_NAME=${DB_USER_NAME}
ENV DB_PASS_WORD=${DB_PASS_WORD}
ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}
ENV KAFKA_HOST=${KAFKA_HOST}
ENV NAVER_CLIENT_ID=${NAVER_CLIENT_ID}
ENV NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET}

RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test --no-daemon

# Stage 2: Run the application using JDK
FROM openjdk:18-jdk-alpine

WORKDIR /app
COPY --from=build /app/booknote/build/libs/booknote-0.0.1-SNAPSHOT.jar /app/booknote.jar

# These environment variables are required during runtime
ENV DB_URL=${DB_URL}
ENV DB_USER_NAME=${DB_USER_NAME}
ENV DB_PASS_WORD=${DB_PASS_WORD}
ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}
ENV KAFKA_HOST=${KAFKA_HOST}
ENV NAVER_CLIENT_ID=${NAVER_CLIENT_ID}
ENV NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET}

EXPOSE 8080
CMD ["java", "-jar", "/app/booknote.jar"]
