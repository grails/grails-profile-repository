import grails.util.*

description( "Creates an Angular controller" ) {
    usage "grails create-ng-controller [NAME]"
    argument name:'Controller Name', description:"The name of the Angular controller to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String type = "Controller"

final String basePath = "grails-app/assets/javascripts/${model.packagePath ?: model.propertyName}"
if (!file("${basePath}/${model.propertyName}.js").exists()) {
    createNgModule(args[0].replaceFirst(~/\.[^\.]+$/, ''))
}

final String testPath = "src/test/assets/${model.packagePath ?: model.propertyName}/controllers/${model.propertyName}${type}Spec.js"

render template: template('NgController.groovy'),
       destination: file("${basePath}/controllers/${model.propertyName}${type}.js"),
       model: [moduleName: model.packageName ?: model.propertyName, name: model.convention(type)],
       overwrite: overwrite
