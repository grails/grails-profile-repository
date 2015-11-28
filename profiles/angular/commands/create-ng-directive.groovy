import grails.util.*
import org.codehaus.groovy.runtime.MetaClassHelper

description( "Creates an Angular directive" ) {
    usage "grails create-ng-directive [NAME]"
    argument name:'Directive Name', description:"The name of the Angular directive to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String basePath = "grails-app/assets/javascripts/${model.packagePath ?: model.propertyName}"
if (!file("${basePath}/${model.propertyName}.js").exists()) {
    createNgModule(args[0].replaceFirst(~/\.[^\.]+$/, ''))
}

render template: template("tests/NgDirectiveSpec.groovy"),
        destination: file("src/test/assets/${model.packagePath ?: model.propertyName}/directives/${model.propertyName}Spec.js"),
        model: [moduleName: model.packageName ?: model.propertyName,
                propertyName: model.propertyName,
                tagName: model.propertyName.replaceAll(/\B[A-Z]/) { '-' + it }.toLowerCase() ],
        overwrite: overwrite


render template: template("NgDirective.groovy"),
       destination: file("${basePath}/directives/${model.propertyName}.js"),
       model: [moduleName: model.packageName ?: model.propertyName,
               propertyName: model.propertyName,
               templatePath: model.packagePath?.replaceAll("\\\\", "/") ?: model.propertyName]
       overwrite: overwrite
