description( "Prints statistics about the project" ) {
    usage "grails stats"
}

def EMPTY = /^\s*$/
def SLASH_SLASH = /^\s*\/\/.*/
def SLASH_STAR_STAR_SLASH = /^(.*)\/\*(.*)\*\/(.*)$/

// TODO - handle slash_star comments inside strings
def DEFAULT_LOC_MATCHER = { file ->
    loc = 0
    comment = 0
    file.eachLine { line ->
        if (line ==~ EMPTY) return
        if (line ==~ SLASH_SLASH) return
        def m = line =~ SLASH_STAR_STAR_SLASH
        if (m.count && m[0][1] ==~ EMPTY && m[0][3] ==~ EMPTY) return
        int open = line.indexOf("/*")
        int close = line.indexOf("*/")
        if (open != -1 && (close-open) <= 1) comment++
        else if (close != -1 && comment) comment--
        if (!comment) loc++
    }
    loc
}

// maps file path to
def pathToInfo = [
    [name: "Controllers",        path: "^grails-app.controllers",      filetype: ["Controller.groovy"]],
    [name: "Interceptors",       path: "^grails-app.controllers",      filetype: ["Interceptor.groovy"]],
    [name: "Domain Classes",     path: "^grails-app.domain",           filetype: [".groovy"]],
    [name: "Jobs",               path: "^grails-app.job",              filetype: [".groovy"]],
    [name: "Services",           path: "^grails-app.services",         filetype: ["Service.groovy"]],
    [name: "Tag Libraries",      path: "^grails-app.taglib",           filetype: ["TagLib.groovy"]],
    [name: "Groovy Helpers",     path: "^src.main.groovy",                  filetype: [".groovy"]],
    [name: "Unit Tests",         path: "^src.test.groovy",                   filetype: [".groovy"]],
    [name: "Scripts",            path: "^src.main.scripts",                     filetype: [".groovy"]],
]

// event("StatsStart", [pathToInfo])

def baseDirFile = baseDir
def baseDirPathLength = baseDirFile.path.size()+1
baseDirFile.eachFileRecurse { file ->
    def match = pathToInfo.find { info ->
        file.path.substring(baseDirPathLength) =~ info.path &&
        info.filetype.any{ s -> file.path.endsWith(s) }
    }
    if (match && file.isFile()) {
        match.filecount = match.filecount ? match.filecount+1 : 1
        // strip whitespace
        loc = match.locmatcher ? match.locmatcher(file) : DEFAULT_LOC_MATCHER(file)
        match.loc = match.loc ? match.loc + loc : loc
    }
}

def totalFiles = 0
def totalLOC = 0

def sw = new StringWriter()
def output = new PrintWriter(sw)

output.println '''
+----------------------+-------+-------+
| Name                 | Files |  LOC  |
+----------------------+-------+-------+'''

pathToInfo.each { info ->
    if (info.filecount) {
        output.println "| " +
            info.name.padRight(20," ") + " | " +
            info.filecount.toString().padLeft(5, " ") + " | " +
            info.loc.toString().padLeft(5," ") + " | "
        totalFiles += info.filecount
        totalLOC += info.loc
    }
}

output.println "+----------------------+-------+-------+"
output.println "| Totals               | " + totalFiles.toString().padLeft(5, " ") + " | " + totalLOC.toString().padLeft(5, " ") + " | "
output.println "+----------------------+-------+-------+\n"

println sw.toString()
