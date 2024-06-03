FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} app.jar

VOLUME /tmp
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]



