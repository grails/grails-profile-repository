description("Stops the running Grails application") {
    usage "grails stop-app"
    synonyms 'stop'
    flag name:'port', description:"Specifies the port which the Grails application is running on (defaults to 8080 or 8443 for HTTPS)"
    flag name:'host', description:"Specifies the host the Grails application is bound to"

}

Integer port = flag('port')?.toInteger() ?: config.getProperty('server.port', Integer) ?: 8080
String host = flag('host') ?: config.getProperty('server.address', String) ?: "localhost"
String path = config.getProperty('server.context-path') ?: config.getProperty('server.contextPath') ?: ""
console.updateStatus "Shutting down application..."
def url = new URL("http://$host:${port}${path}/shutdown")
def connection = url.openConnection()
connection.setRequestMethod("POST")
connection.doOutput = true
connection.connect()
console.updateStatus connection.content.text
while(isServerAvailable(host, port)) {
	sleep 100
}
console.updateStatus "Application shutdown."

