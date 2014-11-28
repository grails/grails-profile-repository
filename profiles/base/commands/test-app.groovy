import org.grails.cli.interactive.completers.*

description("Runs the applications tests") {
    usage "grails test-app [TEST NAME]"
    completer AllClassCompleter
    synonyms 'test'
    argument name:"Test Name", description:"The name of the test to run (optional)", required:false
    flag name:'debug-jvm', target:"-Dtest.debug"
}


arguments = []

// configure environment to test is not specified
if(!commandLine.isEnvironmentSet()) {
    System.setProperty('grails.env', 'test')
}

// add test.single argument if specified
pattern = commandLine.remainingArgsString
if(pattern) {
    arguments << "-Dtest.single=$pattern".toString()
}

// add debug flag if present
debugJvm = flag('debug-jvm')
if(debugJvm) arguments << debugJvm.target 

try {
    gradle.test(*arguments)    
    return true
} catch(e) {
    console.error "FAILED", "Test execution failed"
    return false
}
