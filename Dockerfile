FROM postgres:15-bullseye

LABEL author="Korotkevich Aliaksey"
LABEL description="Postgres Image for reflection"
LABEL version="1.0"

COPY ./src/main/resources/db/*.sql /docker-entrypoint-initdb.d/