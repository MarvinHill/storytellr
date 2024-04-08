FROM gradle:8.6.0-jdk17-alpine as buildstep
COPY . /var/source

WORKDIR /var/source
RUN gradle clean build

FROM amazoncorretto:17 as runstep
COPY --from=buildstep /var/source/build/libs/api-?.?.?.jar var/api/api.jar

WORKDIR /var/api
CMD java -jar api.jar