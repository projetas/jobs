FROM java:8
LABEL maintainer=“salemsaressa3@gmail.com”
VOLUME /tmp
EXPOSE 8080
ADD target/car-service-0.0.1-SNAPSHOT.jar xy-inc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","xy-inc-0.0.1-SNAPSHOT.jar"]