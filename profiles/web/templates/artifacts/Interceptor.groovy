@artifact.package@

class @artifact.name@Interceptor {

  boolean before() { true }

  boolean after() { true }

  void afterView(Throwable t) {
    // no-op
  }

}
