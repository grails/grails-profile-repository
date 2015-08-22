import org.grails.cli.*

description("Runs a Grails application") {
    usage "grails run-app"
    synonyms 'run'
}

allResources = resources("file:**/*.groovy")
args = allResources.findAll { res -> !['Spec', 'Test', 'Tests'].any { res.filename.endsWith(it) }  }
                   .sort { it.filename == "Application.groovy" ? 1 : 0 }
                   .collect { projectPath(it.file) }

args.addAll commandLine.remainingArgs
if(spring.run(*args) == 0) {
    if(!GrailsCli.isInteractiveModeActive()) {
        // block if interactive mode is not running
        while(true) {
            sleep(Long.MAX_VALUE)
        }
    }
}
else {
    return false
}
