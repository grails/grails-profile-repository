@artifact.package@

import grails.dev.commands.ApplicationContextCommand
import org.grails.build.parsing.CommandLine
import org.springframework.context.ConfigurableApplicationContext

class @artifact.name@Command implements ApplicationContextCommand {

  boolean handle(ConfigurableApplicationContext applicationContext, CommandLine commandLine) {
      return false
  }
}
