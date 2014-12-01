import grails.util.BuildSettings
import grails.util.Environment


// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])

if(Environment.current == Environment.DEVELOPMENT) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${BuildSettings.TARGET_DIR}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }

    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false )
}
