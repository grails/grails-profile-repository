import org.grails.cli.interactive.completers.DomainClassCompleter

description("Runs the applications tests") {
    usage "grails test-app [TEST NAME]"
    completer DomainClassCompleter
    argument name:"Test Name", description:"The name of the test to run (optional)", required:false
    flag name:'debug-jvm', target:"-Dtest.debug"
}


arguments = []

pattern = commandLine.remainingArgsString

if(pattern) {
    arguments << "-Dtest.single=$pattern".toString()
}

// add debug flag if present
debugJvm = flag('debug-jvm')
if(debugJvm) arguments << debugJvm.target 

gradle.test(*arguments)