ARG DOCKER_BASE_IMAGE="amazoncorretto"
ARG DOCKER_BASE_IMAGE_TAG="21.0.4-alpine3.18"
ARG APPNAME="app"
ARG MODULE_PATH

FROM ${DOCKER_BASE_IMAGE}:${DOCKER_BASE_IMAGE_TAG}

###
ENV CFG_PATH /opt/config
ENV LOG_PATH /opt/logs

VOLUME /opt/config
VOLUME /opt/logs

# JAVA options
ENV JAVA_DEFAULT_OPTS "-Djava.net.preferIPv4Stack=true -Dnetworkaddress.cache.ttl=0 \
    -XX:+UnlockExperimentalVMOptions \
    -XX:+UseContainerSupport \
    -XX:MinRAMPercentage=25.0 \
    -XX:MaxRAMPercentage=75.0 \
    -XX:InitialRAMPercentage=25.0 \
    -Dnetworkaddress.cache.negative.ttl=0 \
    -Xdebug -Xnoagent \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.local.only=false \
    -Djava.rmi.server.hostname=127.0.0.1 \
    -Djava.security.egd=file:/dev/./urandom"

ENV JDWP_PORT 8940
ENV JMXREMOTE_PORT 8950

# Additional JAVA options
ENV JAVA_OPTS ""

# Additional JAR options
ENV JAR_OPTS ""

COPY target/*.jar /opt/app.jar

WORKDIR /opt/

CMD exec java \
    ${JAVA_DEFAULT_OPTS} \
    -XX:OnOutOfMemoryError="kill -2 %p" \
    --enable-preview \
#    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=${JDWP_PORT} \
    -Dcom.sun.management.jmxremote.port=${JMXREMOTE_PORT} \
    -Dcom.sun.management.jmxremote.rmi.port=${JMXREMOTE_PORT} \
    ${JAVA_OPTS} \
    -jar /opt/app.jar \
    ${JAR_OPTS}

