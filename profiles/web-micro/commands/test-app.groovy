description("Tests a Grails application") {
    usage "grails test-app"
    synonyms 'test'
}

args = ['Application.groovy']
args.addAll commandLine.remainingArgs
return spring.test(*args) == 0 ? true : false



