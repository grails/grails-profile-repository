description("Runs a Grails application") {
    usage "grails run-app"
    synonyms 'run'
    flag name:'debug-jvm'
}


// add debug flag if present
if(flag('debug-jvm')) {
    gradle."run --debug-jvm"(*commandLine.remainingArgs)
}
else {
    gradle.run(*commandLine.remainingArgs)
}


