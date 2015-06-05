description("Runs a Grails application") {
    usage "grails run-app"
    synonyms 'run'
    flag name:'debug-jvm', description:"Starts the JVM in debug mode allowing attachment of a remote debugger"
    flag name:'https', description:"Starts Grails in HTTPS mode on port 8443"
    flag name:'port', description:"Specifies the port which to start Grails on (defaults to 8080 or 8443 for HTTPS)"
}

if(!commandLine.isEnvironmentSet()) {
    System.setProperty('grails.env', 'dev')
} else {
    System.setProperty('grails.env', commandLine.environment)
}
// add debug flag if present
try {

    def arguments = []
    arguments.addAll commandLine.remainingArgs

    def port = flag('port')
    if(port) {
        arguments << "-Dgrails.server.port=$port"
    }

    if(flag('https')) {
        if(!port) {
            arguments << "-Dgrails.server.port=8443"
        }
        if(!System.getProperty('grails.server.ssl.key-store')) {
            def keystoreDir = new File(buildDir, "ssl")
            def keystoreFile = new File(keystoreDir, 'keystore')

            boolean keyStoreParametersAvailable = false
            if(!keystoreFile.exists()) {
                console.updateStatus "Generating SSL certificate"
                if ( createSSLCertificate(keystoreDir) ) {
                    keyStoreParametersAvailable = true
                    console.updateStatus "Generated SSL certificate"
                }
                else {
                    console.warn "Unable to automatically generate SSL certificate, manual configuration required. Set 'server.ssl.key-store' in application.yml"   
                }
                
            }
            else {
                keyStoreParametersAvailable = true
            }
            if(keyStoreParametersAvailable) {
                arguments << "-Dgrails.server.ssl.key-store=${keystoreFile}"
                arguments << "-Dgrails.server.ssl.key-store-password=123456"
                arguments << "-Dgrails.server.ssl.key-password=123456"

            }
        }
    }

    if(flag('debug-jvm')) {
        gradle."bootRun --debug-jvm"(*arguments)
    }
    else {
        gradle.bootRun(*arguments)
    }
}
catch(org.gradle.tooling.BuildCancelledException e) {
    console.updateStatus("Application stopped")
    return true
}
catch(Throwable e) {
    console.error "Failed to start server", e
    return false
}


protected boolean createSSLCertificate(File keystoreDir) {

    if (!keystoreDir.exists() && !keystoreDir.mkdir()) {
        throw new RuntimeException("Unable to create keystore folder: $keystoreDir.canonicalPath")
    }

    def keyToolClass = getKeyToolClass()
    if(keyToolClass) {
        try {
            def keystoreFile = new File(keystoreDir, "keystore")
            keyToolClass.main(
                "-genkey",
                "-alias", "localhost",
                "-dname", "CN=localhost,OU=Test,O=Test,C=US",
                "-keyalg", "RSA",
                "-validity", "365",
                "-storepass", "key",
                "-keystore", keystoreFile.absolutePath,
                "-storepass", "123456",
                "-keypass", "123456")
        }
        catch(Throwable e) {
            return false
        }
        
        return true
    }
    else {
        return false
    }
}

protected Class getKeyToolClass() {
    try {
        try {
            return Class.forName( 'sun.security.tools.KeyTool' )
        }
        catch (ClassNotFoundException e) {
            // no try/catch for this one, if neither is found let it fail
            return Class.forName( 'com.ibm.crypto.tools.KeyTool' )
        }
        
    }
    catch(Throwable e) {
        return null
    }
    
}
