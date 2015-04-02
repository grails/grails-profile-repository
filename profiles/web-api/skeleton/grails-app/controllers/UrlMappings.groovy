class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'index')
        "500"(controller: 'InternalServerError')
        "404"(controller: 'NotFound')
    }
}
