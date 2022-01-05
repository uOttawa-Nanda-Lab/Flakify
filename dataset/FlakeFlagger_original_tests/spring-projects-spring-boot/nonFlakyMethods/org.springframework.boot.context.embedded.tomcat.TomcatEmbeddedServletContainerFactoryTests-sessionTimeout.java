@Test public void sessionTimeout() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  factory.setSessionTimeout(10);
  assertTimeout(factory,1);
}
