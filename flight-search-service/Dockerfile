# Start with a base image containing Java runtime (Java 17)
FROM openjdk:17-slim

# Add Maintainer Info
LABEL maintainer="hassanemad15697@gmail.com"

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
