import grails.util.*
import org.codehaus.groovy.runtime.MetaClassHelper

description( "Creates an Angular service" ) {
    usage "grails create-ng-service [NAME]"
    argument name:'Service Name', description:"The name of the Angular controller to create", required: true
    flag name:'type', description:"The type of service to create. Possible values are service, factory, value, provider, constant"
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')
String typeFlag = flag('type') ?: "factory"

if (!["service", "factory", "value", "provider", "constant"].contains(typeFlag)) {
    error "Service type \"${typeFlag}\" is not a valid option"
} else {
    final String type = GrailsNameUtils.getClassName(typeFlag)
    String name = model.propertyName

    if (!["Constant", "Value"].contains(type) && !name.endsWith(type)) {
        name += type
    }

    final String modulePath = model.packagePath ?: model.propertyName
    final String moduleName = model.packageName ?: model.propertyName

    final String basePath = "grails-app/assets/javascripts/${modulePath}"
    if (!file("${basePath}/${moduleName}.js").exists()) {
        createNgModule(moduleName)
    }

    render template: template("tests/NgServiceSpec.js"),
            destination: file("src/test/assets/${modulePath}/services/${name}Spec.js"),
            model: [moduleName: moduleName, name: name],
            overwrite: overwrite

    render template: template("services/Ng${type}.js"),
            destination: file("${basePath}/services/${name}.js"),
            model: [moduleName: moduleName, name: name],
            overwrite: overwrite
}

