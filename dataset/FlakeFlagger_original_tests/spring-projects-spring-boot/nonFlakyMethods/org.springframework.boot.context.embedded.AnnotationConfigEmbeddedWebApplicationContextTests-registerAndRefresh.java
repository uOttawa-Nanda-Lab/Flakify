@Test public void registerAndRefresh() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(ExampleEmbeddedWebApplicationConfiguration.class);
  this.context.refresh();
  verifyContext();
}
