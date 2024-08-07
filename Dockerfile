FROM openjdk:17 AS final

EXPOSE 8080

COPY ./build/libs/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
