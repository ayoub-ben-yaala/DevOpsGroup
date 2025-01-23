FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/tp-foyer-5.0.9.jar tp-foyer-5.0.9.jar
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.9.jar"]
