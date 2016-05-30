node('docker'){
    stage 'Build'
        // Mark the code checkout 'stage'....
        //build 'sia_build'
        git 'https://github.com/joherma1/sia.git'
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn --version
        sh "${mvnHome}/bin/mvn compile"
}

