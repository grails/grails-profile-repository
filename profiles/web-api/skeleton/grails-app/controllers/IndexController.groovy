import grails.converters.JSON
import grails.core.GrailsApplication

class IndexController {

  def GrailsApplication grailsApplication
  
  def index() {
    render(contentType: 'application/json') {
      message = "Welcome to Grails!"
      environment = grails.util.Environment.current.name
      appversion = grailsApplication.metadata['info.app.version']
      grailsversion = grailsApplication.metadata['info.app.grailsVersion']
      appprofile = grailsApplication.config.grails?.profile
      groovyversion = GroovySystem.getVersion()
      jvmversion = System.getProperty('java.version')
      controllers = array {
        for (c in grailsApplication.controllerClasses)
        controller([name: c.fullName])
      }
      plugins = array {
        for (p in grailsApplication.mainContext.getBean('pluginManager').allPlugins)
        plugin([name: p.fullName])
      }
    }

  }

}
