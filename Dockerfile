FROM amazoncorretto:17-alpine-jdk
MAINTAINER Diealarcon
COPY market-0.0.1-SNAPSHOT  market-crypto.jar
ENTRYPOINT ["java","-jar","market-crypto.jar"]