import grails.util.*

description( "Creates an Angular directive" ) {
    usage "grails create-ng-directive [NAME]"
    argument name:'Directive Name', description:"The name of the Angular directive to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String modulePath = model.packagePath ?: model.propertyName
final String moduleName = model.packageName ?: model.propertyName

final String basePath = "grails-app/assets/javascripts/${modulePath.replaceAll('\\\\', '/')}"
if (!file("${basePath}/${moduleName}.js").exists()) {
    createNgModule(moduleName)
}

render template: template("tests/NgComponentSpec.js"),
        destination: file("src/test/javascripts/${modulePath}/directives/${model.propertyName}Spec.js"),
        model: [moduleName: moduleName,
                propertyName: model.propertyName,
                tagName: GrailsNameUtils.getScriptName(model.propertyName)],
        overwrite: overwrite

render template: template("NgComponent.js"),
       destination: file("${basePath}/directives/${model.propertyName}.js"),
       model: [moduleName: moduleName,
               propertyName: model.propertyName,
               templatePath: model.packagePath?.replaceAll("\\\\", "/") ?: model.propertyName,
               controllerName: "${model.className}Controller"]
       overwrite: overwrite

File htmlTemplate = file("${basePath}/templates/${model.propertyName}.tpl.html")
if (!htmlTemplate.exists()) {
   htmlTemplate.getParentFile().mkdirs()
   htmlTemplate.createNewFile()
   addStatus "Created blank template at ${projectPath(htmlTemplate)}"
}
