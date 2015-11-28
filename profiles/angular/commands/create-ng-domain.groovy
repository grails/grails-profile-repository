import grails.util.*
import org.codehaus.groovy.runtime.MetaClassHelper

description( "Creates an Angular domain" ) {
    usage "grails create-ng-domain [NAME]"
    argument name:'Domain Name', description:"The name of the Angular domain to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

final String basePath = "grails-app/assets/javascripts/${model.packagePath ?: model.propertyName}"
if (!file("${basePath}/${model.propertyName}.js").exists()) {
    createNgModule(args[0].replaceFirst(~/\.[^\.]+$/, ''))
}

render template: template("tests/NgDomainSpec.groovy"),
        destination: file("src/test/assets/${model.packagePath ?: model.propertyName}/domain/${model.className}Spec.js"),
        model: [moduleName: model.packageName ?: model.propertyName,
                className: model.className]
        overwrite: overwrite


render template: template("NgDomain.groovy"),
       destination: file("${basePath}/domain/${model.className}.js"),
       model: [moduleName: model.packageName ?: model.propertyName,
               propertyName: model.propertyName,
               className: model.className]
       overwrite: overwrite
