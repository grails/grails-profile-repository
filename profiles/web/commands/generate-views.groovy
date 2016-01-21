import org.grails.cli.interactive.completers.DomainClassCompleter

description( "Generates GSP views for the specified domain class" ) {
    usage "grails generate-views [DOMAIN CLASS]|*"
    argument name:'Domain Class', description:"The name of the domain class, or '*' for all", required:true
    completer DomainClassCompleter
    flag name:'force', description:"Whether to overwrite existing files"
}

if(args) {
    def classNames = args
    if(args[0] == '*') {
        classNames = resources("file:grails-app/domain/**/*.groovy").collect { className(it) }
    }
    for(arg in classNames) {
        def sourceClass = source(arg)
        def overwrite = flag('force') ? true : false
        if(sourceClass) {
            def model = model(sourceClass)
            render template: template('artifacts/scaffolding/edit.gsp'),
                    destination: file("grails-app/views/${model.propertyName}/edit.gsp"),
                    model: model,
                    overwrite: overwrite

            render template: template('artifacts/scaffolding/create.gsp'),
                    destination: file("grails-app/views/${model.propertyName}/create.gsp"),
                    model: model,
                    overwrite: overwrite

            render template: template('artifacts/scaffolding/index.gsp'),
                    destination: file("grails-app/views/${model.propertyName}/index.gsp"),
                    model: model,
                    overwrite: overwrite

            render template: template('artifacts/scaffolding/show.gsp'),
                    destination: file("grails-app/views/${model.propertyName}/show.gsp"),
                    model: model,
                    overwrite: overwrite


            addStatus "Views generated for ${projectPath(sourceClass)}"
        } else {
            error "Domain class not found for name $arg"
        }
    }
} else {
    error "No domain class specified"
}
