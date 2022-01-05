@Test public void jetty() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(JettyConfig.class);
  doTest(this.context,"/hello");
}
