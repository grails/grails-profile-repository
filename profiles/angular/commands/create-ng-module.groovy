import grails.util.*

description( "Creates an Angular module" ) {
    usage "grails create-ng-module [NAME]"
    argument name:'Module Name', description:"The name of the Angular module to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = [:]
model.fullName = args[0]
model.propertyName = GrailsNameUtils.getPropertyName(model.fullName)
model.packageName = GrailsNameUtils.getPackageName(model.fullName)
model.packagePath = model.packageName.replace('.' as char, File.separatorChar)

boolean overwrite = flag('force')

final String folder = "grails-app/assets/javascripts/${model.packagePath}/${model.propertyName}"

Map dependencies = [:]

if (file("grails-app/assets/javascripts/${model.packagePath}/core/core.js").exists()) {
    String moduleName = "\"${model.packageName}.core\""
    String assetPath = "/${model.packagePath}/core/core"
    dependencies[moduleName] = assetPath
}

render template: template('NgModule.js'),
       destination: file("${folder}/${model.propertyName}.js"),
       model: [fullName: model.fullName, rootPath: model.packagePath, dependencies: dependencies],
       overwrite: overwrite

["controllers", "directives", "domain", "services", "templates"].each {
    fileSystemInteraction.mkdir "${folder}/${it}"
}
