description("Runs a Grails application") {
    usage "grails run-app"
    synonyms 'run'
    flag name:'debug-jvm'
}


// add debug flag if present
try {
	if(flag('debug-jvm')) {
	    gradle."run --debug-jvm"(*commandLine.remainingArgs)
	}
	else {
	    gradle.run(*commandLine.remainingArgs)
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


