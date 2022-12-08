FROM openjdk:11
ADD target/elasticservice.jar /etlservices/elasticservice/elasticservice.jar
WORKDIR /etlservices/elasticservice
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "elasticservice.jar"]