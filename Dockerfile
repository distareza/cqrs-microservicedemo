FROM openjdk:8
COPY target/cqrs-microservicedemo-0.0.1-SNAPSHOT.jar .

ENTRYPOINT [ "java", "-jar" , "cqrs-microservicedemo-0.0.1-SNAPSHOT.jar" ]

