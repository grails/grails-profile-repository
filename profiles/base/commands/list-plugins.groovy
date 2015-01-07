description("Lists available plugins from the Plugin Repository") {
    usage "grails list-plugins"
}

try {
	console.addStatus "Available Plugins"
	def text = new URL('http://repo.grails.org/grails/plugins3/org/grails/plugins/').text
	text.eachMatch(/<a href="([a-z-]+)\/">.+/) {
		console.log "* ${it[1]}"
	}
}
catch(Throwable e) {
	console.error "Failed to list plugins", e
	return false
}


