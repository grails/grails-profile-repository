description("Runs the applications tests") {
    usage "grails test-app [TEST NAME]"
    completer TestsCompleter
    synonyms 'test'
    argument name:"Test Name", description:"The name of the test to run (optional)", required:false
    flag name:'debug-jvm'
    flag name:'unit', description:"Run unit tests (test target)"
    flag name:'integration', description:"Run integration tests (integrationTest target)"
    flag name:'clean', description:"Re-run all tests (cleanTest cleanIntegrationTest target)"
}

// configure environment to test is not specified
if(!commandLine.isEnvironmentSet()) {
    System.setProperty('grails.env', 'test')
}

// add test.single argument if specified
def testsFilter = commandLine.remainingArgs.collect { "--tests $it".toString() }.join(' ')?.trim()
boolean debugJvm = flag('debug-jvm')

def handleTestPhase = { targetName ->
    def args = []
    if(flag('clean')) {
        args << "clean${targetName.capitalize()}"
    }
    args << targetName
    if(testsFilter) {
        args << testsFilter
    }
    // add debug flag if present
    if(debugJvm) {
        args << "--debug-jvm"
    }
    args
}

def gradleArgs = []

boolean executeUnitTests = flag('unit') || !flag('integration')
if(executeUnitTests) {
    gradleArgs += handleTestPhase('test')
} 

boolean hasIntegrationTests = file("src/integration-test").isDirectory()
boolean executeIntegrationTests = hasIntegrationTests && (flag('integration') || !flag('unit'))
if(executeIntegrationTests) {
    gradleArgs += handleTestPhase('integrationTest')
}

try {
    gradle."${gradleArgs.join(' ')}"()
    addStatus "Tests PASSED"
    return true
} catch(e) {
    console.error "Tests FAILED", "Test execution failed"
    return false
}
