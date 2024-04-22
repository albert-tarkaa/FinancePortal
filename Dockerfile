FROM eclipse-temurin:17-jdk-focal
LABEL authors="Albert Tarkaa"

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve-plugins

COPY src ./src

RUN ./mvnw package

ENTRYPOINT ["java", "-jar", "target/libraryportal.jar"]