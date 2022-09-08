FROM openjdk:17-jdk
MAINTAINER joony <bestheroz@gmail.com>
VOLUME /tmp
COPY build/libs/*.jar app.jar

RUN mkdir /logs

EXPOSE 8000

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar -server -Dfile.encoding=UTF-8" ]
