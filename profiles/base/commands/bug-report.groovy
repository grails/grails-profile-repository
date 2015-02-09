import java.text.*

description( "Creates a zip file that can be attached to issue reports for the current project" ) {
  usage "grails bug-report"
}

String fileName = baseDir.name
String date = new SimpleDateFormat("ddMMyyyy").format(new Date())
String zipName = "${buildDir}/${fileName}-bug-report-${date}.zip"

ant.zip(destfile: zipName, filesonly: true) {
    fileset(dir: baseDir.canonicalPath) {
        include name: 'grails-app/**'
        include name: 'src/**'
        include name: 'build.gradle'
    }
}

console.addStatus "Created bug report ZIP: ${projectPath(zipName)}"
