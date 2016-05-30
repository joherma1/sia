node('docker'){
    stage 'Build'
        //build 'sia_build'
        git branch: 'develop', url: 'https://github.com/joherma1/sia.git'
        def v = version()
        if (v) {
            echo "Building verions ${v}"
        }
        sh "docker buid -t joherma1/rpi-sia ."
        
    //TODO
    //stage 'Analyze'
    
    stage 'Test'
        //Create a test database
        docker run -e POSTGRES_PASSWORD=agricultura.1 -e POSTGRES_USER=sia --name postgres-sia-test -d joherma1/rpi-postgres
        //Initialize database
        docker run -i --link postgres-sia-test:postgres --rm -e POSTGRES_PASSWORD=agricultura.1 -e POSTGRES_USER=sia -e POSTGRES_SCHEMA=sia joherma1/rpi-sia /bin/bash -c 'cd /opt/sia; mvn exec:java -Dexec.mainClass=org.sysreg.sia.model.InitializeDatabase'
        //Run tests
        docker run -i --link postgres-sia-test:postgres --rm -e POSTGRES_PASSWORD=agricultura.1 -e POSTGRES_USER=sia -e POSTGRES_SCHEMA=sia joherma1/rpi-sia /bin/bash -c 'cd /opt/sia; mvn test'
        docker run -i --link postgres-sia-test:postgres --rm -e POSTGRES_PASSWORD=agricultura.1 -e POSTGRES_USER=sia -e POSTGRES_SCHEMA=sia joherma1/rpi-sia /bin/bash -c 'cd /opt/sia; mvn integration-test'
        //Remove test database
        docker stop postgres-sia-test
        docker rm -v postgres-sia-test
        //catchError {
        //   
        //}
        
}

def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    matcher ? matcher[0][1] : null
}
