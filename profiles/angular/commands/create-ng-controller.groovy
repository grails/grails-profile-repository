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

final String fileName = model.propertyName.endsWith(type) ? model.propertyName : "${model.propertyName}${type}"
final String controllerName = model.className.endsWith(type) ? model.className : model.convention(type)

render template: template('tests/NgControllerSpec.js'),
       destination: file("src/test/javascripts/${modulePath}/controllers/${fileName}Spec.js"),
       model: [moduleName: moduleName, name: controllerName],
       overwrite: overwrite

render template: template('NgController.js'),
       destination: file("${basePath}/controllers/${fileName}.js"),
       model: [moduleName: moduleName, name: controllerName],
       overwrite: overwrite
