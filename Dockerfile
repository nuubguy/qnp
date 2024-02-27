# Stage 1: Build the application
FROM gradle:7.0.2-jdk11 AS build
WORKDIR /home/gradle/project
COPY . /home/gradle/project
RUN gradle clean build

# Stage 2: Run the application
FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
