# Build the JAR file outside of Docker
# RUN ./mvnw clean package

# Start from the Java base image
FROM eclipse-temurin:22-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file into the Docker image
# Make sure to build the JAR file before running Docker build
COPY ./target/*.jar /app/*.jar
# Start the application
ENTRYPOINT ["java", "-jar", "/app/*.jar"]


#-----

#FROM openjdk:17-jdk-alpine
#COPY ./target /app
#WORKDIR /app
##ENTRYPOINT ["java","-cp",".","org.example.MainServiceApplication"]
#ENTRYPOINT ["java", "-jar", "/app/*.jar"]

#------

#FROM openjdk:17-jdk-alpine
#
#COPY target/main-service-*.jar app.jar
#COPY target/generated-sources/ /generated-sources/
#
#VOLUME /tmp
#EXPOSE 8080
#
## Specify the entrypoint
#ENTRYPOINT ["java","-cp","app.jar:/generated-sources/","org.example.MainServiceApplication"]

#------

#FROM openjdk:17-jdk-alpine
#
#ARG JAR_FILE=target/*.jar
#
#ADD ${JAR_FILE} app.jar
#
#VOLUME /tmp
#EXPOSE 8080
#
#ENTRYPOINT ["java","-jar","/app.jar"]



