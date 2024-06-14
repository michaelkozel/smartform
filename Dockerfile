FROM openjdk:22-ea-28-jdk
ARG JAR_FILE=build/libs/smartform.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]