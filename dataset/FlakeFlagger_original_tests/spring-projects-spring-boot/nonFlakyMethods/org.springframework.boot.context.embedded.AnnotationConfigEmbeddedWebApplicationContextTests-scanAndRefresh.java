@Test public void scanAndRefresh() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.scan(ExampleEmbeddedWebApplicationConfiguration.class.getPackage().getName());
  this.context.refresh();
  verifyContext();
}
