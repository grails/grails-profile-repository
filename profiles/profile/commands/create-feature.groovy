description( "Creates a new profile feature" ) {
  usage "grails create-feature [Feature Name NAME]"
  argument name:'Feature Name', description:"The name of the feature", required:true
}

if(args) {
    def fn = args[0].trim()
    def dir = file("features/${fn}").canonicalPath
    mkdir( dir )
    mkdir( "features/${fn}/skeleton/grails-app/conf" )
    render( '''
# customize configuration here
# my:
#   setting: true
#''', file("features/${fn}/skeleton/grails-app/conf/application.yml"))

    render( '''
description: Description of the feature
# customize versions here
# dependencies:
#   compile:
#     - "org.grails.plugins:myplugin:1.0"
#''', file("features/${fn}/feature.yml") )

    console.addStatus "Feature created at ${projectPath(dir)}"
}
else {
    error "No command name specified"
}
