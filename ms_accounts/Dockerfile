#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#container info
LABEL maintainer="fseijo fseijowork@gmail.com"
LABEL description="This is a test Docker's image"
LABEL version="1.0"
LABEL license="Fseijo Java School"

#Add the application's jar to the container
COPY target/ms_accounts-0.0.1-SNAPSHOT.jar ms_accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java", "-jar", "ms_accounts-0.0.1-SNAPSHOT.jar"]