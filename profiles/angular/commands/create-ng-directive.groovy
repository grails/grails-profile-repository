import grails.util.*
import org.codehaus.groovy.runtime.MetaClassHelper

description( "Creates an Angular directive" ) {
    usage "grails create-ng-directive [NAME]"
    argument name:'Directive Name', description:"The name of the Angular directive to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String modulePath = model.packagePath ?: model.propertyName
final String moduleName = model.packageName ?: model.propertyName

final String basePath = "grails-app/assets/javascripts/${modulePath}"
if (!file("${basePath}/${moduleName}.js").exists()) {
    createNgModule(moduleName)
}

render template: template("tests/NgDirectiveSpec.groovy"),
        destination: file("src/test/assets/${modulePath}/directives/${model.propertyName}Spec.js"),
        model: [moduleName: moduleName,
                propertyName: model.propertyName,
                tagName: model.propertyName.replaceAll(/\B[A-Z]/) { '-' + it }.toLowerCase() ],
        overwrite: overwrite


render template: template("NgDirective.groovy"),
       destination: file("${basePath}/directives/${model.propertyName}.js"),
       model: [moduleName: moduleName,
               propertyName: model.propertyName,
               templatePath: model.packagePath?.replaceAll("\\\\", "/") ?: model.propertyName]
       overwrite: overwrite
