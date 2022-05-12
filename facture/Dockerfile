FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} facture.jar
ENTRYPOINT ["java","-jar","/facture.jar"]
EXPOSE 8081