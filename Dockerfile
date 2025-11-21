# -------------------------
# Build Stage
# -------------------------
FROM maven:3.9.4-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first for caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY . .

# Build the Spring Boot jar
RUN mvn clean package -DskipTests

# -------------------------
# Run Stage
# -------------------------
FROM openjdk:17-jdk-slim-bullseye
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
