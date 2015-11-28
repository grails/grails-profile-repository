import grails.util.*

description( "Creates an Angular module" ) {
    usage "grails create-ng-module [NAME]"
    argument name:'Module Name', description:"The name of the Angular module to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String folder = "grails-app/assets/javascripts/${model.packagePath}/${model.propertyName}"

List dependencies = ["\"ngResource\""]

if (file("grails-app/assets/javascripts/siteConfig.js").exists()) {
    dependencies.add("\"siteConfig\"")
}

render template: template('NgModule.groovy'),
       destination: file("${folder}/${model.propertyName}.js"),
       model: [fullName: model.fullName, dependencies: dependencies],
       overwrite: overwrite

["controllers", "directives", "domain", "services", "templates"].each {
    fileSystemInteraction.mkdir "${folder}/${it}"
}

