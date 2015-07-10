description("Runs the Grails interactive console") {
    usage "grails console"
    flag name:'debug-jvm', description:"Starts the JVM in debug mode allowing attachment of a remote debugger"
    flag name:'verbose', description:"Show more output from the build during startup"
}

def isIm = org.grails.cli.GrailsCli.isInteractiveModeActive()
def arguments = []

def con = getConsole()
if( !(flag('verbose') || con.verbose)) {
    arguments << '--quiet'
}

arguments.addAll commandLine.remainingArgs

def g = isIm ? gradle.async : gradle
con.updateStatus "Running console..."
if(flag('debug-jvm')) {
    g."console --debug-jvm"(*arguments)
}
else {
    g."console"(*arguments)
}
