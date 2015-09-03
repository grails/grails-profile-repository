import javax.management.remote.JMXServiceURL
import javax.management.remote.JMXConnectorFactory
import javax.management.ObjectName
import org.grails.io.support.*

description("Stops the running Grails application") {
    usage "grails stop-app"
    synonyms 'stop'
    flag name:'port', description:"Specifies the port which the Grails application is running on (defaults to 8080 or 8443 for HTTPS)"
    flag name:'host', description:"Specifies the host the Grails application is bound to"
}
System.setProperty("run-app.running", "false")
def getJMXLocalConnectorAddresses = {->
	final applicationMainClassName = MainClassFinder.findMainClass()

	if(applicationMainClassName) {
		try {
		    final String CONNECTOR_ADDRESS = "com.sun.management.jmxremote.localConnectorAddress"
		    def VirtualMachine = getClass().classLoader.loadClass('com.sun.tools.attach.VirtualMachine')
		    return VirtualMachine.list()
		        .findAll { 
		        	it.displayName() == applicationMainClassName 
		        }
		        .collect { desc ->
		            def vm = VirtualMachine.attach(desc.id())
		            try {
		                def connectorAddress = vm.agentProperties.getProperty(CONNECTOR_ADDRESS)
		                if (connectorAddress == null) {
		                    // Trying to load agent
		                    def agent = [vm.systemProperties.getProperty("java.home"), "lib", "management-agent.jar"].join(File.separator)
		                    vm.loadAgent(agent)

		                    connectorAddress = vm.agentProperties.getProperty(CONNECTOR_ADDRESS)
		                }
		                if (connectorAddress) {
		                    return connectorAddress
		                }
		            } finally {
		                vm.detach()
		            }
		        }.findAll { it }
				
		}
		catch(Throwable e) {
			// fallback to REST request if JMX not available
		}
	}
}


def addresses = getJMXLocalConnectorAddresses() 
if(addresses) {
	JMXServiceURL url = new JMXServiceURL(addresses[0])
	def connector = JMXConnectorFactory.connect(url)

	try {
	    def server = connector.MBeanServerConnection

	    def objectName = server.queryNames(null,null).find {  it.canonicalName.contains('name=shutdownEndpoint,type=Endpoint') }
	    def mbean = new GroovyMBean(server, objectName)
    	console.addStatus "Shutting down application..."
        mbean.shutdown()
        console.addStatus "Application shutdown."
        return true
    }
    catch(e) {
		console.addStatus "Application not found via JMX, attempting remote shutdown."
    }
	finally {
	    connector.close()
	}	
}


Integer port = flag('port')?.toInteger() ?: config.getProperty('server.port', Integer) ?: 8080
String host = flag('host') ?: config.getProperty('server.address', String) ?: "localhost"
String path = config.getProperty('server.context-path') ?: config.getProperty('server.contextPath') ?: ""
console.updateStatus "Shutting down application..."
def url = new URL("http://$host:${port}${path}/shutdown")
try {
	def connection = url.openConnection()
	connection.setRequestMethod("POST")
	connection.doOutput = true
	connection.connect()
	console.updateStatus connection.content.text
	while(isServerAvailable(host, port)) {
		sleep 100
	}
	console.updateStatus "Application shutdown."
	return true
	
}
catch (e) {
	console.error "Application not running."		
	return false
}
	


