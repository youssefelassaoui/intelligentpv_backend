# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Add the Spring Boot jar file to the container
COPY target/intelligent-pv-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
