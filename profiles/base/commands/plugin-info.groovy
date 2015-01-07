import groovy.xml.*

description("Prints information about the given plugin") {
    usage "grails plugin-info [PLUGIN NAME]"
    argument name:"Plugin Name", description:"The name of the plugin"
}

def pluginRepoURL = "http://repo.grails.org/grails/plugins3/org/grails/plugins"
def pluginName = args[0]
try {
	console.addStatus "Plugin Info: ${pluginName}"
	def mavenMetadata = new XmlSlurper().parseText(new URL("${pluginRepoURL}/${pluginName}/maven-metadata.xml").text)
	def latestVersion = mavenMetadata.versioning.latest.text()
	console.addStatus "Latest Version: ${latestVersion}"
	console.addStatus "All Versions: ${mavenMetadata.versioning.versions.version*.text().join(',')}"

	
	def pluginInfo 
	if(latestVersion.endsWith('-SNAPSHOT')) {
		def versionMetadata = new XmlSlurper().parseText(new URL("${pluginRepoURL}/${pluginName}/${latestVersion}/maven-metadata.xml").text)
		def snapshotVersion = versionMetadata.version.text()
		pluginInfo = new XmlSlurper().parseText(new URL("${pluginRepoURL}/${pluginName}/${latestVersion}/${pluginName}-${snapshotVersion}-plugin.xml").text)
	}
	else {
		pluginInfo = new XmlSlurper().parseText(new URL("${pluginRepoURL}/${pluginName}/${latestVersion}/${pluginName}-${latestVersion}-plugin.xml").text)
	}

	if(pluginInfo) {
		console.addStatus "Title: ${pluginInfo.title.text()}"
		def desc = pluginInfo.description.text()
		if(desc) {
			console.log('')
			console.log(desc)
			console.log('')
		}

		console.log "* License: ${pluginInfo.license.text()}"

		if(pluginInfo.documentation) {
			console.log "* Documentation: ${pluginInfo.documentation.text()}"
		}
		if(pluginInfo.issueManagement) {
			console.log "* Issue Tracker: ${pluginInfo.issueManagement.@url.text()}"
		}		
		if(pluginInfo.scm) {
			console.log "* Source: ${pluginInfo.scm.@url.text()}"
		}				
	}

}
catch(Throwable e) {
	console.error "Failed to display plugin info: ${e.message}", e
	return false
}


