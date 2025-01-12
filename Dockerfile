FROM openjdk:17-jdk-slim

ENV SPRING_DATASOURCE_URL jdbc:mysql://mysql:3306/challenge_db
ENV SPRING_DATASOURCE_USERNAME root
ENV SPRING_DATASOURCE_PASSWORD admin
ENV SPRING_MAIL_HOST sandbox.smtp.mailtrap.io
ENV SPRING_MAIL_PORT 2525
ENV SPRING_MAIL_USERNAME de4dcba4c36519
ENV SPRING_MAIL_PASSWORD 6caf19d626fcff

WORKDIR /app

COPY target/Patient-Registration-Backend-Challenge-0.0.1-SNAPSHOT.jar /app/app.jar
COPY wait-for-it.sh /app/wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["/app/wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app/app.jar"]

