@Test public void sessionScopeAvailable() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ExampleEmbeddedWebApplicationConfiguration.class,SessionScopedComponent.class);
  verifyContext();
}
