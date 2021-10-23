FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/ROOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} dnd5.jar
COPY ./src/main/resources ./src/main/resources
ENTRYPOINT ["java","-jar","dnd5.jar"]