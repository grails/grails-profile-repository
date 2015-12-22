import grails.util.*

description( "Creates an Angular service" ) {
    usage "grails create-ng-service [NAME]"
    argument name:'Service Name', description:"The name of the Angular controller to create", required: true
    flag name:'type', description:"The type of service to create. Possible values are service, factory, value, provider, constant"
    flag name:'force', description:"Whether to overwrite existing files"
}

def serviceModel = model(args[0])
boolean overwrite = flag('force')
String typeFlag = flag('type') ?: "factory"

if (!["service", "factory", "value", "provider", "constant"].contains(typeFlag)) {
    error "Service type \"${typeFlag}\" is not a valid option"
} else {
    final String type = GrailsNameUtils.getClassName(typeFlag)
    String name = serviceModel.propertyName

    Map typeSuffixes = [service: "Service", factory: "Service", provider: "Provider"]
    if (typeSuffixes.containsKey(typeFlag) && !name.endsWith(typeSuffixes[typeFlag])) {
        name += typeSuffixes[typeFlag]
    }

    final String modulePath = serviceModel.packagePath ?: serviceModel.propertyName
    final String moduleName = serviceModel.packageName ?: serviceModel.propertyName

    def moduleModel = model(moduleName)

    final String basePath = "grails-app/assets/javascripts/${modulePath}"
    if (!file("${basePath}/${moduleModel.propertyName}.js").exists()) {
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

