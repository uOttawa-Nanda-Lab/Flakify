@Test public void contextAlreadyHasDispatcherServletWithDefaultName() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(DispatcherServletConfiguration.class,BaseConfiguration.class);
  verifyContext();
}
