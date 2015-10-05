description( "Creates a new template for the profile" ) {
  usage "grails create-template [Template Name]"
  argument name:'Template Name', description:"The name of the template", required:true
  flag name:'force', description:"Whether to overwrite existing files"
}

if(args) {
	def model = model( args[0] )
    render template:'templates/GeneratorTemplate.groovy',
		   destination:file("templates/${model.packagePath}/${model.simpleName}.groovy"),
		   model: model,
		   overwrite: flag('force')

}
else {
    error "No command name specified"
}
