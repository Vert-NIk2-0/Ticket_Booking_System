FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/mybookingsystem-0.0.1-SNAPSHOT.jar app.jar

RUN apt-get update && apt-get install -y postgresql-client && apt-get clean

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
