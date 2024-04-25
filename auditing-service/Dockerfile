# Start with a base image containing Java runtime (Java 17)
FROM openjdk:17-jdk-alpine

# Add Maintainer Info
LABEL maintainer="hassanemad15697@gmail.com"


# The application's jar file
ARG JAR_FILE=target/*.jar

# Copy the JAR to a different directory
COPY ${JAR_FILE} auditing.jar

# Change the entry point
ENTRYPOINT ["java","-jar","/auditing.jar"]