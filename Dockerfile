FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/tp-foyer-5.0.18.jar tp-foyer-5.0.18.jar
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.18.jar"]