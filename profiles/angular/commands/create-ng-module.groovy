import grails.util.*

description( "Creates an Angular module" ) {
    usage "grails create-ng-module [NAME]"
    argument name:'Module Name', description:"The name of the Angular module to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String folder = "grails-app/assets/javascripts/${model.packagePath}/${model.propertyName}"

render template: template('NgModule.groovy'),
       destination: file("${folder}/${model.propertyName}.js"),
       model: model,
       overwrite: overwrite

["controllers", "directives", "domain", "services", "templates"].each {
    fileSystemInteraction.mkdir "${folder}/${it}"
}

