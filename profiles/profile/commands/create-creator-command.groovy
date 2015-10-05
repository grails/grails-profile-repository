description( "Creates a new create-* command for the profile" ) {
  usage """grails create-creator-command [Command Name] [Target Dir] [Convention]

Example: grails create-creator-command Controller controllers"""
  argument name:'Command Name', description:"The name of the command", required:true
  argument name:'Target Directory', description:"The target directory", required:true
  argument name:'Convention', description:"The convention to use for file names, defaults to command name", required:false
  flag name:'force', description:"Whether to overwrite existing files"
}

if(args) {
    def scriptName = GrailsNameUtils.getClassNameForLowerCaseHyphenSeparatedName(args[0])
    def model = model( scriptName ).asMap()
    model.put('targetDirectory', args[1])
    model.put('convention', args.size() == 3 ? args[2] : model.simpleName)
    

    render template:'commands/CreatorCommand.groovy',
           destination:file("commands/create-${model.lowerCaseName}.groovy"),
           model: model,
           overwrite: flag('force')

    render template:'templates/GeneratorTemplate.groovy',
           destination:file("templates/${args[1]}/${model.simpleName}.groovy"),
           model: model,
           overwrite: flag('force')           
}
else {
    error "No command name specified"
}
