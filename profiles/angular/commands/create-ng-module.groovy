import grails.util.*

description( "Creates an Angular module" ) {
    usage "grails create-ng-module [NAME]"
    argument name:'Module Name', description:"The name of the Angular module to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}
addStatus args[0]
def model = model(args[0])

boolean overwrite = flag('force')

final String basePath = "grails-app/assets/javascripts"
final String modulePath = "${model.packagePath}/${model.propertyName}"
final String moduleName = "${model.packageName}.${model.propertyName}"

Map dependencies = [:]

if (file("${basePath}/${model.packagePath}/core/${model.packageName}.core.js").exists()) {
    String coreModuleName = "\"${model.packageName}.core\""
    String assetPath = "/${model.packagePath.replaceAll('\\\\','/')}/core/${model.packageName}.core"
    dependencies[coreModuleName] = assetPath
}

render template: template('tests/NgModuleSpec.js'),
        destination: file("src/test/javascripts/${modulePath}/${moduleName}Spec.js"),
        model: [fullName: moduleName],
        overwrite: overwrite

render template: template('NgModule.js'),
       destination: file("${basePath}/${modulePath}/${moduleName}.js"),
       model: [fullName: moduleName, dependencies: dependencies],
       overwrite: overwrite
