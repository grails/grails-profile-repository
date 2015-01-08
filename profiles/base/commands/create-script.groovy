description( "Creates a Grails script" ) {
  usage "grails create-script [SCRIPT NAME]"
  argument name:'Script Name', description:"The name of the script to create"
  flag name:'force', description:"Whether to overwrite existing files"
}

def scriptName = args[0]
def model = model(scriptName)
def overwrite = flag('force') ? true : false

render template: template('artifacts/Script.groovy'), 
     destination: file("src/main/scripts/${model.lowerCaseName}.groovy"),
     model: model,
     overwrite: overwrite
