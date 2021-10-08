FROM docker-registry-ro.lab.nordigy.ru/platform/platform-base:ol7s-openjre-8-1.8.0_302-2021-09-05_18-42-01

ENV APP_NAME=oldDnd5

ENV JAVA_OPTS="-Djava.util.logging.config.file=1 -Dspring.profiles.active=prod -Dlogging.logstash=true -Dlogging.logstash.access=true -Djava.locale.providers=CLDR -Dmail.mime.encodeparameters=false -Dspring.config.name=application,smtp"

ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8 -Djava.locale.providers=CLDR"

RUN groupadd uns && useradd -g uns uns
RUN chown -R uns.uns /etc \
    && chown -R uns.uns /mnt

COPY ./templateStorage/ ./templateStorage/
COPY ./metadataStorage/ ./metadataStorage/

RUN chown -R uns.uns .

USER uns

# install
COPY ./uns/build/libs/*.jar ./

RUN ln -sf /etc/ringcentral/uns config

CMD ./start.sh

