package @grails.codegen.defaultPackage@

import grails.boot.GrailsApp
import grails.boot.config.GrailsWebConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

@EnableAutoConfiguration
class Application extends GrailsWebConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application)
    }
}