import grails.util.*

description( "Creates a ${simpleName}" ) {
    usage "grails create-${lowerCaseName} [NAME]"
    argument name:'${simpleName} Name', description:"The name of the ${simpleName} to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def model = model(args[0])
boolean overwrite = flag('force')

render template: template('${targetDirectory}/${simpleName}.groovy'),
       destination: file("grails-app/${targetDirectory}/\${model.packagePath}/\${model.simpleName}.groovy"),
       model: model,
       overwrite: overwrite
