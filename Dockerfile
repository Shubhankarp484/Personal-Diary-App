# ---------- build stage ----------
FROM maven:3.9.9-eclipse-temurin-11 AS builder
WORKDIR /usr/src/app

# Copy pom.xml and Maven wrapper first (for caching dependencies)
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Ensure mvnw is executable
RUN chmod +x mvnw

# Download dependencies (for caching)
RUN ./mvnw -B -DskipTests dependency:go-offline

# Copy source code and resources
COPY src ./src
COPY src/main/resources/application.yml.example src/main/resources/application.yml

# Build the JAR
RUN ./mvnw -B package -DskipTests \
    && cp target/*.jar app.jar
