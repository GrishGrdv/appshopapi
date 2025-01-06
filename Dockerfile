FROM openjdk:21
COPY build/libs/*.jar appshopapi-0.1-all.jar
EXPOSE 8080
CMD java -jar appshopapi-0.1-all.jar
