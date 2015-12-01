import grails.util.*

description( "Creates an Angular controller" ) {
    usage "grails create-ng-controller [NAME]"
    argument name:'Controller Name', description:"The name of the Angular controller to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String type = "Controller"

final String modulePath = model.packagePath ?: model.propertyName
final String moduleName = model.packageName ?: model.propertyName

final String basePath = "grails-app/assets/javascripts/${modulePath}"
if (!file("${basePath}/${moduleName}.js").exists()) {
    createNgModule(moduleName)
}

render template: template('tests/NgControllerSpec.groovy'),
       destination: file("src/test/assets/${modulePath}/controllers/${model.propertyName}${type}Spec.js"),
       model: [moduleName: moduleName, name: model.convention(type)],
       overwrite: overwrite


render template: template('NgController.groovy'),
       destination: file("${basePath}/controllers/${model.propertyName}${type}.js"),
       model: [moduleName: moduleName, name: model.convention(type)],
       overwrite: overwrite
