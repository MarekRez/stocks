FROM maven:3.9.6-eclipse-temurin-21 as builder
LABEL Miroslav Rucka <miroslav.rucka@posam.sk>

WORKDIR /application

COPY pom.xml .

# TODO: ak nedefinujes child module build nezbehne:
# 14.15 [ERROR] [ERROR] Some problems were encountered while processing the POMs:
# 14.15 [ERROR] Child module /application/domain of /application/pom.xml does not exist @ 
COPY domain domain
COPY outbound-repository-jpa outbound-repository-jpa
COPY inbound-controller-ws inbound-controller-ws
COPY springboot springboot
COPY api-spec api-spec

RUN mvn package -DskipTests
RUN mkdir build && cd build && java -Djarmode=layertools -jar ../springboot/target/*.jar extract

FROM eclipse-temurin:21.0.1_12-jdk-alpine
WORKDIR /application

# TODO: na --from=builder pls review
COPY --from=builder application/build/dependencies/ ./
COPY --from=builder application/build/spring-boot-loader/ ./
COPY --from=builder application/build/snapshot-dependencies/ ./
COPY --from=builder application/build/application/ ./

RUN addgroup -S boot && adduser -S boot -G boot
USER boot

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} org.springframework.boot.loader.launch.JarLauncher"]
