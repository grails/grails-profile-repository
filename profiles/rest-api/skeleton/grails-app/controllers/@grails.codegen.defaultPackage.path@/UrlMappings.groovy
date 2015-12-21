package @grails.codegen.defaultPackage@

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'application', 'index')
        "500"(view: '/application/serverError')
        "404"(view: '/application/notFound')
    }
}
