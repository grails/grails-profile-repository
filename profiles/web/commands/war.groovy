description("Creates a WAR file for deployment to a container (like Tomcat)") {
    usage "grails [ENV] war"

    synonyms 'assemble'
    flag name:'clean', description:"Execute 'clean' prior to creating WAR"
}

// configure environment to production is not specified
if(!commandLine.isEnvironmentSet()) {
    System.setProperty('grails.env', 'production')
} else {
    System.setProperty('grails.env', commandLine.environment)
}

def arguments = []
commandLine.systemProperties.each { key, value ->
    arguments << "-D${key}=$value".toString()
}


gradle."assemble"(*arguments)

buildPath = projectPath("${buildDir}/libs")
addStatus "Built application to $buildPath using environment: ${grails.util.Environment.current.name}"
