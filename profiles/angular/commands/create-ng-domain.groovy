import grails.util.*
import groovy.io.FileType

description( "Creates an Angular domain" ) {
    usage "grails create-ng-domain [NAME]"
    argument name:'Domain Name', description:"The name of the Angular domain to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def domainModel = model(args[0])
boolean overwrite = flag('force')

final String javascriptRoot = "grails-app/assets/javascripts/"

def hasFactoryFile = {
    boolean factoryFileExists = false
    file(javascriptRoot).eachFileRecurse(FileType.FILES) { file ->
        if (file.name == 'DomainServiceFactory.js') {
            factoryFileExists = true
        }
    }
    factoryFileExists
}

final String modulePath = domainModel.packagePath ?: domainModel.propertyName
final String moduleName = domainModel.packageName ?: domainModel.propertyName

def moduleModel = model(moduleName)

final String basePath = "grails-app/assets/javascripts/${modulePath}"
if (!file("${basePath}/${moduleModel.propertyName}.js").exists()) {
    createNgModule(moduleName)
}

render template: template("tests/NgDomainSpec.js"),
        destination: file("src/test/assets/${modulePath}/domain/${domainModel.className}Spec.js"),
        model: [moduleName: moduleName,
                className: domainModel.className]
        overwrite: overwrite

String templateName = hasFactoryFile() ? 'NgDomainFactory.js' : 'NgDomain.js'

render template: template(templateName),
       destination: file("${basePath}/domain/${domainModel.className}.js"),
       model: [moduleName: moduleName,
               propertyName: domainModel.propertyName,
               className: domainModel.className]
       overwrite: overwrite
