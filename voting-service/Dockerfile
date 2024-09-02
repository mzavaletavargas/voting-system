# Use an official Java runtime as a parent image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files first
# This helps in leveraging Docker cache when dependencies haven't changed
COPY build.gradle settings.gradle /app/

# Copy the gradle wrapper files
COPY gradlew /app/
COPY gradle /app/gradle

# Download the dependencies, so that they are cached in the Docker layer
RUN ./gradlew dependencies

# Copy the source code to the container
COPY src /app/src

# Build the application
RUN ./gradlew build -x test

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "build/libs/voting-service-0.0.1-SNAPSHOT.jar"]
