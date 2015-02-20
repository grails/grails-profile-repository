@artifact.package@

import grails.dev.commands.ApplicationContextCommand
import groovy.transform.*
import groovy.util.logging.Commons
import org.grails.build.parsing.CommandLine
import org.springframework.context.ConfigurableApplicationContext

@CompileStatic
@EqualsAndHashCode
@Commons
class @artifact.name@Command implements ApplicationContextCommand {

  @Override
  boolean handle(ConfigurableApplicationContext applicationContext, CommandLine commandLine) {
      return false
  }
}
