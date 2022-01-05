@Test public void createFromScan() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ExampleEmbeddedWebApplicationConfiguration.class.getPackage().getName());
  verifyContext();
}
