FROM ubuntu:latest
LABEL authors="danieltenkorang"

# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the application JAR file to the container
COPY target/*.jar app.jar

# Expose the specified port (replace YOUR_PORT with the actual port)
EXPOSE 18085

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]