description( "TODO: command description" ) {
  usage "grails ${lowerCaseName}"

  // TODO: populate arguments
  argument name:'arg1', description:"arg1 description", required:true

  // TODO: populate flags
  flag name:'flag1', description:"flag1 description"
}

if(args) {
    // TODO: implement command
    gradle.${taskName}()
}
else {
    error "No argument specified"
}
