description( "Creates a new command that invokes Gradle for the profile" ) {
  usage "grails create-gradle-command [COMMAND NAME]"
  argument name:'Command Name', description:"The name of the command", required:true
  argument name:'Task Name', description:"The name of Gradle the task", required:true
  flag name:'force', description:"Whether to overwrite existing files"
}

if(args) {
	def scriptName = GrailsNameUtils.getClassNameForLowerCaseHyphenSeparatedName(args[0])
	def model = model( scriptName ).asMap()
	model.taskName = args[1]
    render template:'commands/GradleCommand.groovy',
		   destination:file("commands/${model.lowerCaseName}.groovy"),
		   model: model,
		   overwrite: flag('force')

}
else {
    error "No command name specified"
}
