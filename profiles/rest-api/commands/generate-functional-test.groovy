import org.grails.cli.interactive.completers.DomainClassCompleter

description( "Generates a functional test for a controller that performs REST operations" ) {
  usage "grails generate-functional-test [DOMAIN CLASS]"
  argument name:'Domain Class', description:"The name of the domain class", required:true
  completer DomainClassCompleter
  flag name:'force', description:"Whether to overwrite existing files"
}

if(args) {
  def classNames = args
  if(args[0] == '*') {
    classNames = resources("file:grails-app/domain/**/*.groovy").collect { className(it) }
  }
  for(arg in classNames) {
    def sourceClass = source(arg)
    boolean overwrite = flag('force')
    if(sourceClass) {
      def model = model(sourceClass)
      render template: template('artifacts/scaffolding/FunctionalSpec.groovy'),
             destination: file("src/integration-test/groovy/${model.packagePath}/${model.convention('FunctionalSpec')}.groovy"),
             model: model,
             overwrite: overwrite


      addStatus "Scaffolding completed for ${projectPath(sourceClass)}"
    }
    else {
      error "Domain class not found for name $arg"
    }
  }
}
else {
    error "No domain class specified"
}
