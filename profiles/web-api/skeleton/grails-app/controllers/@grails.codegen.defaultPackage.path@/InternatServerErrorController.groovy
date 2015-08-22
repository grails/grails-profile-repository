package @grails.codegen.defaultPackage@

class InternalServerErrorController {

    def index() {
        render(contentType: 'application/json') {
            error = 500
            message = "Internal server error"
        }
    }
}
