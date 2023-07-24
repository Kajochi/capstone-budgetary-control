FROM openjdk:20

ENV ENVIRONMENT=prod

LABEL maintainer="christoph.groneberg@neuefische.de"

EXPOSE 8080

ADD backend/target/budgetaryControl.jar app.jar

CMD [ "sh", "-c", "java -jar /app.jar" ]