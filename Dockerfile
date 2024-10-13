FROM openjdk:17-oracle
ARG JAR_FILE=target/spring-data-jpa-demo.jar
ADD ${JAR_FILE} /
ENTRYPOINT ["java", "-jar", "spring-data-jpa-demo.jar"]