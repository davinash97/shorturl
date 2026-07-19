# Stage-1: Build

FROM maven:eclipse-temurin AS builder

WORKDIR /app

COPY pom.xml .
COPY .env .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage-2: Runtime

FROM eclipse-temurin:17.0.18_8-jre

WORKDIR /app

# Copy the JAR from builder stage
COPY --from=builder /app/target/url-shortener-0.0.1.jar app.jar
COPY --from=builder /app/.env .env

# Expose port (change if your app uses a different one)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
