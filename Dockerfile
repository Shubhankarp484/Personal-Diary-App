# ---------- build stage ----------
FROM maven:3.8.7-eclipse-temurin-11 AS builder
WORKDIR /usr/src/app

# Copy pom.xml and Maven wrapper first (for caching dependencies)
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw -B -DskipTests dependency:go-offline

# Copy source code and
COPY src ./src

# Copy application.yml from the example to be used in the build
COPY src/main/resources/application.yml.example src/main/resources/application.yml

# build the JAR
RUN ./mvnw -B package -DskipTests \
    && cp target/*.jar app.jar

# ---------- runtime stage ----------
FROM openjdk:11-jre-slim
# Create a non-root user for safety
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
WORKDIR /app

# Copy only the built JAR from builder stage
COPY --from=builder /usr/src/app/app.jar /app/app.jar

# Allow custom JVM options at runtime
ENV JAVA_OPTS=""
# Render/Heroku will inject PORT — app must bind to it
EXPOSE 8080

USER appuser

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=${PORT:-10000} -jar /app/app.jar"]
