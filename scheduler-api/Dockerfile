FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/scheduler-api-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]