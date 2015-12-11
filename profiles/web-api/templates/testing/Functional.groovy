@artifact.package@

import grails.test.mixin.integration.Integration
import grails.transaction.*
import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder

@Integration
@Rollback
class @artifact.name@Spec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "Test the homepage"() {
        when:"The home page is requested"
            def resp = restBuilder().get("$baseUrl/")

        then:"The response is correct"
            resp.status == OK.value()
            resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
            resp.json.message == 'Welcome to Grails!'
    }

    RestBuilder restBuilder() {
        new RestBuilder()
    }
}
