@Test public void tomcat() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(TomcatConfig.class);
  doTest(this.context,"/hello");
}
