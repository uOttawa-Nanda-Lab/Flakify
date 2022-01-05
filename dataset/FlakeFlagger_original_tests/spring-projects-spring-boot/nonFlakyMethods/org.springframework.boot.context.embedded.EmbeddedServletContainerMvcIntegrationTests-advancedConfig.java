@Test public void advancedConfig() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(AdvancedConfig.class);
  doTest(this.context,"/example/spring/hello");
}
