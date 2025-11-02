# ---------- STAGE 1: BUILD ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Stel environment variables in (default values)
ENV SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8080 \
    DB_URL=jdbc:postgresql://localhost:5432/mydb \
    DB_USER=appuser \
    DB_PASSWORD=changeme

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
