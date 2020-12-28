FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY ./build/libs/catalogue-search-service-0.0.1-SNAPSHOT.jar /opt/app
WORKDIR /opt/app
ENTRYPOINT ["java","-jar","catalogue-search-service-0.0.1-SNAPSHOT.jar"]
