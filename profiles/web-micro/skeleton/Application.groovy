@Grab("org.grails.plugins:hibernate:4.3.5.5.BUILD-SNAPSHOT")
@Grab("h2")
import grails.persistence.Entity

@Entity
@Resource(uri="/examples")
class Example {
    String name
}