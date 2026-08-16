FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml /app/
COPY export-user/pom.xml /app/export-user/
COPY export-organization/pom.xml /app/export-organization/

RUN mvn -pl export-user dependency:go-offline -B

COPY export-user/src /app/export-user/src
RUN mvn -pl export-user clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/export-user/target/export-user-*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
