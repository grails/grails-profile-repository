description("Packages a Grails application") {
    usage "grails package"
    synonyms 'jar', 'assemble'
}

args = ['Application.groovy']
args.addAll commandLine.remainingArgs
return spring.jar(*args) == 0 ? true : false



