import grails.util.*

description( "Creates an Angular directive" ) {
    usage "grails create-ng-directive [NAME]"
    argument name:'Directive Name', description:"The name of the Angular directive to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def directiveModel = model(args[0])
boolean overwrite = flag('force')

final String modulePath = directiveModel.packagePath ?: directiveModel.propertyName
final String moduleName = directiveModel.packageName ?: directiveModel.propertyName

def moduleModel = model(moduleName)

final String basePath = "grails-app/assets/javascripts/${modulePath}"
if (!file("${basePath}/${moduleModel.propertyName}.js").exists()) {
    createNgModule(moduleName)
}

render template: template("tests/NgDirectiveSpec.js"),
        destination: file("src/test/assets/${modulePath}/directives/${directiveModel.propertyName}Spec.js"),
        model: [moduleName: moduleName,
                propertyName: directiveModel.propertyName,
                tagName: GrailsNameUtils.getScriptName(directiveModel.propertyName)],
        overwrite: overwrite


render template: template("NgDirective.js"),
       destination: file("${basePath}/directives/${directiveModel.propertyName}.js"),
       model: [moduleName: moduleName,
               propertyName: directiveModel.propertyName,
               templatePath: directiveModel.packagePath?.replaceAll("\\\\", "/") ?: directiveModel.propertyName]
       overwrite: overwrite
