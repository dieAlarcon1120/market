FROM amazoncorretto:17-alpine-jdk
MAINTAINER Diealarcon
COPY target/market-0.0.1-SNAPSHOT  market-crypto.jar
ENTRYPOINT ["java","-jar","market-crypto.jar"]