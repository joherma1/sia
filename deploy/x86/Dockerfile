FROM java:7-jdk

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

COPY src /opt/sia/src
COPY pom.xml /opt/sia/

RUN cd /opt/sia && \
    mvn dependency:go-offline

# mvn clean, validate, compile, test, package, integration-test, install
RUN cd /opt/sia && \
    mvn clean install -Dmaven.test.skip=true

EXPOSE 8080
# TODO download offline repositories
# tomcat embebed and dependencies
CMD mvn -f /opt/sia/pom.xml tomcat7:run
