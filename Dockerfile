# # Use an official OpenJDK runtime as a parent image
# FROM openjdk:17-jdk-slim

# # Set the working directory in the container
# WORKDIR /app

# # Copy the Maven wrapper and the pom.xml file
# COPY mvnw .
# COPY .mvn .mvn
# COPY pom.xml .

# # Download the Maven dependencies
# RUN ./mvnw dependency:go-offline

# # Copy the project files
# COPY src src

# # Package the application
# RUN ./mvnw package -DskipTests

# # Expose the port the application runs on
# EXPOSE 8080

# # Run the Spring Boot application
# CMD ["java", "-jar", "target/comrade-mart-back-0.0.1-SNAPSHOT.jar"]
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN  mvn clean package -DskipTests

FROM  openjdk:17.0.1-jdk-slim
COPY --from=build /target/ironheart-0.0.1-SNAPSHOT.jar ironheart.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","ironheart.jar" ]