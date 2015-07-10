description("Runs the Grails interactive shell") {
    usage "grails shell"
    synonyms 'sh'
}

console.error "The Grails shell must be run from Gradle using 'gradle shell -q'"
return false
