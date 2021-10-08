FROM openjdk:8-jdk-alpine
MAINTAINER dnd5.club
COPY target/root.jar
ENTRYPOINT ["java","-jar","/root.jar"]