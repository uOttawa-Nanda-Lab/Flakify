@Test public void createFromConfigClass() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(BaseConfiguration.class);
  verifyContext();
}
