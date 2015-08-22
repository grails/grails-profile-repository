description("Packages a Grails application") {
    usage "grails package"
    synonyms 'jar', 'assemble'
}

allResources = resources("file:**/*.groovy")

appName = config.getProperty('info.app.name', String) ?: baseDir.parentFile.name
appVersion = config.getProperty('info.app.version', String) ?: '0.1'
jar = file("${buildDir}/${appName}-${appVersion}.jar")
args = [ jar.absolutePath ]
args.addAll allResources.findAll { res -> !['Spec', 'Test', 'Tests'].any { res.filename.endsWith(it) }  }
                        .sort { it.filename == "Application.groovy" ? 1 : 0 }
                        .collect { projectPath(it.file) }

args.addAll commandLine.remainingArgs
if(spring.jar(*args) == 0) {
    addStatus "Built JAR file ${projectPath(jar)}"
}
else {
    error "Error building JAR file"
}
