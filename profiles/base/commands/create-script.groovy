import grails.util.*

description( "Creates a Grails script" ) {
    usage "grails create-script [SCRIPT NAME]"
    argument name:'Script Name', description:"The name of the script to create", required:true
    flag name:'force', description:"Whether to overwrite existing files"
}

def scriptName = GrailsNameUtils.getClassNameForLowerCaseHyphenSeparatedName(args[0])
def model = model(scriptName)
boolean overwrite = flag('force')

render template: template('artifacts/Script.groovy'),
       destination: file("src/main/scripts/${model.lowerCaseName}.groovy"),
       model: model,
       overwrite: overwrite
