FROM eclipse-temurin:17-jdk-focal
LABEL authors="Albert Tarkaa"

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy Maven configuration files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Resolve Maven dependencies
RUN mvn dependency:resolve-plugins

# Copy source code
COPY src ./src

# Package the application
RUN mvn package

# Set entry point
ENTRYPOINT ["java", "-jar", "target/libraryPortal.jar"]