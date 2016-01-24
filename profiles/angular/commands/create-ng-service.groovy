import grails.util.*

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

    Map typeSuffixes = [service: "Service", factory: "Service", provider: "Provider"]
    if (typeSuffixes.containsKey(typeFlag) && !name.endsWith(typeSuffixes[typeFlag])) {
        name += typeSuffixes[typeFlag]
    }

    final String modulePath = model.packagePath ?: model.propertyName
    final String moduleName = model.packageName ?: model.propertyName
    final String assetPath = config.getProperty("grails.codegen.angular.assetDir", String) ?: "javascripts"

    final String basePath = "grails-app/assets/${assetPath}/${modulePath}"
    if (!file("${basePath}/${moduleName}.js").exists()) {
        createNgModule(moduleName)
    }

    render template: template("tests/NgServiceSpec.js"),
            destination: file("src/test/javascripts/${modulePath}/services/${name}Spec.js"),
            model: [moduleName: moduleName, name: name],
            overwrite: overwrite

    render template: template("services/Ng${type}.js"),
            destination: file("${basePath}/services/${name}.js"),
            model: [moduleName: moduleName, name: name],
            overwrite: overwrite
}

