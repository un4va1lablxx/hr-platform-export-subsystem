FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml /app/
COPY export-user/pom.xml /app/export-user/
COPY export-organization/pom.xml /app/export-organization/

RUN mvn -pl export-organization dependency:go-offline -B

COPY export-organization/src /app/export-organization/src
RUN mvn -pl export-organization clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/export-organization/target/export-organization-*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
