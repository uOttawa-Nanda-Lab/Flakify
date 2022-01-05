@Test public void testMetadataComplete() throws Exception {
  Tomcat tomcat=new Tomcat();
  tomcat.setBaseDir("src/test/resources");
  tomcat.setPort(12345);
  StandardContext context=new StandardContext();
  context.setName("test3");
  context.setDocBase("test3");
  context.setPath("/test3");
  context.addLifecycleListener(new ContextConfig());
  tomcat.getHost().addChild(context);
  tomcat.start();
  Assert.assertEquals(0,context.getApplicationLifecycleListeners().length);
  tomcat.stop();
  tomcat.destroy();
}
