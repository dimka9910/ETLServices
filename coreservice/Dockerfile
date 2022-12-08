FROM openjdk:11
ADD target/classes/vk /etlservices/vk
ADD target/coreservice.jar /etlservices/coreservice/coreservice.jar
WORKDIR /etlservices/coreservice
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "coreservice.jar"]