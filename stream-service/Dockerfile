# Use an official Java runtime as a parent image
FROM eclipse-temurin:21-jdk-alpine as builder

# Install Maven
RUN apk add --no-cache maven

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml /app/
COPY src /app/src

# Install dependencies and package the application
RUN mvn package -DskipTests

# Second stage: create a smaller runtime image
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy only the necessary files from the builder stage
COPY --from=builder /app/target/stream-service-0.0.1-SNAPSHOT.jar /app/stream-service.jar

# Expose the port the app runs on
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "/app/stream-service.jar"]
