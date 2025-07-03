# Build stage
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy từ thư mục gốc dự án (context đã được đặt ở docker-compose.yml)
COPY ../../pom.xml .
COPY ../../src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]