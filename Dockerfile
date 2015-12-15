FROM java:7-jdk

RUN apt-get update && apt-get install -y \
    git \
    maven

RUN mkdir -p /opt/sia/src && \
    git clone --depth=1 https://github.com/joherma1/sia.git /opt/sia/src

RUN cd /opt/sia/src && \
    mvn dependency:go-offline

# mvn clean, validate, compile, test, package, integration-test, install
RUN cd /opt/sia/src && \
    mvn clean install -Dmaven.test.skip=true

EXPOSE 8080
# TODO download offline repositories
# tomcat embebed and dependencies
CMD mvn -f /opt/sia/src/pom.xml tomcat7:run
