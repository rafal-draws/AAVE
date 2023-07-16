FROM sbtscala/scala-sbt:eclipse-temurin-focal-17.0.5_8_1.9.2_2.13.11

WORKDIR /app

COPY . /app

ENV DATA_STORAGE /app/data

VOLUME ["/app/data"]

CMD ["sbt", "run"]