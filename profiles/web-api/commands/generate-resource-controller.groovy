import org.grails.cli.interactive.completers.DomainClassCompleter

description( "Generates a controller that performs REST operations" ) {
  usage "grails generate-resource-controller [DOMAIN CLASS]"
  argument name:'Domain Class', description:"The name of the domain class", required:true
  completer DomainClassCompleter
  flag name:'force', description:"Whether to overwrite existing files"
}


if(args) {
  def classNames = args
  if(args[0] == '*') {
    classNames = resources("file:grails-app/domain/**/*.groovy")
                    .collect { className(it) }
  }
  for(arg in classNames) {
    def sourceClass = source(arg)
    def overwrite = flag('force') ? true : false
    if(sourceClass) {
      def model = model(sourceClass)
      render template: template('artifacts/scaffolding/Controller.groovy'), 
             destination: file("grails-app/controllers/${model.packagePath}/${model.convention('Controller')}.groovy"),
             model: model,
             overwrite: overwrite

      // TODO: Produce a test
      // render template: template('scaffolding/Spec.groovy'), 
      //        destination: file("src/test/groovy/${model.packagePath}/${model.convention('ControllerSpec')}.groovy"),
      //        model: model,
      //        overwrite: overwrite


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