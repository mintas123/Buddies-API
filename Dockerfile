FROM openjdk:17.0.2-jdk-slim
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]