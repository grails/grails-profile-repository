@Grab("hibernate")
@Grab("h2")
import grails.persistence.Entity

@Entity
@Resource(uri="/examples")
class Example {
    String name
}
