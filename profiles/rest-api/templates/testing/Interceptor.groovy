@artifact.package@

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(@artifact.name@Interceptor)
class @artifact.name@InterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test @artifact.propertyName@ interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"@artifact.propertyName@")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
