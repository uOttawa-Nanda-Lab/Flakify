@Test public void testSpringContextLoaderListenerInWebXml() throws Exception {
  Tomcat tomcat=new Tomcat();
  tomcat.setBaseDir("src/test/resources");
  tomcat.setPort(12345);
  StandardContext context=new StandardContext();
  context.setName("test");
  context.setDocBase("test");
  context.setPath("/test");
  context.addLifecycleListener(new ContextConfig());
  tomcat.getHost().addChild(context);
  tomcat.start();
  Assert.assertEquals(1,context.getApplicationLifecycleListeners().length);
  Assert.assertTrue(context.getApplicationLifecycleListeners()[0] instanceof ContextLoaderListener);
  tomcat.stop();
  tomcat.destroy();
}
