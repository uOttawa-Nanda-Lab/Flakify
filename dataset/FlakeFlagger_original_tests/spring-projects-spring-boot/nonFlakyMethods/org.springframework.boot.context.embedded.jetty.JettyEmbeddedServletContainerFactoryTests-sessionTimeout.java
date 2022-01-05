@Test public void sessionTimeout() throws Exception {
  JettyEmbeddedServletContainerFactory factory=getFactory();
  factory.setSessionTimeout(10);
  assertTimeout(factory,10);
}
