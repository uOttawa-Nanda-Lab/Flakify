@Test public void containerHasNoServletContext() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(EnsureContainerHasNoServletContext.class,BaseConfiguration.class);
  verifyContext();
}
