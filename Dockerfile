FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/smartform.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]