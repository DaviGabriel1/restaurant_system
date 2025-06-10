FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/restaurant_system.jar restaurant_system.jar
EXPOSE 8000
CMD ["java","-jar","restaurant_system.jar"]