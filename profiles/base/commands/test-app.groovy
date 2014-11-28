import org.grails.cli.interactive.completers.*

description("Runs the applications tests") {
    usage "grails test-app [TEST NAME]"
    completer AllClassCompleter
    synonyms 'test'
    argument name:"Test Name", description:"The name of the test to run (optional)", required:false
    flag name:'debug-jvm'
}


arguments = []

// configure environment to test is not specified
if(!commandLine.isEnvironmentSet()) {
    System.setProperty('grails.env', 'test')
}

// add test.single argument if specified
args = commandLine.remainingArgs.collect { "--tests $it".toString() }.join(' ')

// add debug flag if present
try {
    if(flag('debug-jvm')) {
        gradle."test $args --debug-jvm"(*arguments)        
    }
    else {
        gradle."test $args"(*arguments)        
    }    
    addStatus "Tests PASSED"
    return true
} catch(e) {
    console.error "Tests FAILED", "Test execution failed"
    return false
}
