FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/oldDND5.jar
WORKDIR /opt/app
COPY ${JAR_FILE} oldDND5.jar
ENTRYPOINT ["java","-jar","oldDND5.jar"]