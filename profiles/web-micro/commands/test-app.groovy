description("Tests a Grails application") {
    usage "grails test-app"
    synonyms 'test'
}

allResources = resources("file:**/*.groovy")
args =  allResources
               .sort { it.filename == "Application.groovy" ? 1 : 0 }
               .collect { projectPath(it.file) }

args.addAll commandLine.remainingArgs
if(spring.test(*args) == 0) {
    addStatus "Tests PASSED"
}
else {
    error "Tests FAILED"
}