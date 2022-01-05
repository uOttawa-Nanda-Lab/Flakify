@Test public void createFromConfigClass() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ExampleEmbeddedWebApplicationConfiguration.class);
  verifyContext();
}
