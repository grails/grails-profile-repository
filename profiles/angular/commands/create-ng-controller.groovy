import grails.util.*

description( "Creates an Angular controller" ) {
    usage "grails create-ng-controller [NAME]"
    argument name:'Controller Name', description:"The name of the Angular controller to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def controllerModel = model(args[0])
boolean overwrite = flag('force')

final String type = "Controller"

final String modulePath = controllerModel.packagePath ?: controllerModel.propertyName
final String moduleName = controllerModel.packageName ?: controllerModel.propertyName

def moduleModel = model(moduleName)

final String basePath = "grails-app/assets/javascripts/${modulePath}"
if (!file("${basePath}/${moduleModel.propertyName}.js").exists()) {
    createNgModule(moduleName)
}

final String fileName = controllerModel.propertyName.endsWith(type) ? controllerModel.propertyName : "${controllerModel.propertyName}${type}"
final String controllerName = controllerModel.className.endsWith(type) ? controllerModel.className : controllerModel.convention(type)

render template: template('tests/NgControllerSpec.js'),
       destination: file("src/test/assets/${modulePath}/controllers/${fileName}Spec.js"),
       model: [moduleName: moduleName, name: controllerName],
       overwrite: overwrite

render template: template('NgController.js'),
       destination: file("${basePath}/controllers/${fileName}.js"),
       model: [moduleName: moduleName, name: controllerName],
       overwrite: overwrite
