FROM openjdk:21 as builder
ARG JAR_FILE="./build/libs/justice-*.jar"
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:21

RUN mkdir /app

ARG CONF_FILE="./env/dev/conf/docker.yml"
COPY ${CONF_FILE} /app/app.yml

COPY --from=builder dependencies/ /app/
COPY --from=builder snapshot-dependencies/ /app/
COPY --from=builder spring-boot-loader/ /app/
COPY --from=builder application/ /app/

WORKDIR /app

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher", "--spring.config.location=file:/app/app.yml"]
