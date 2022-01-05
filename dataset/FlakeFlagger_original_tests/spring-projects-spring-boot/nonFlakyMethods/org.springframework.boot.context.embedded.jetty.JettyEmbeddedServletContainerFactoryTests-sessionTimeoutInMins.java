@Test public void sessionTimeoutInMins() throws Exception {
  JettyEmbeddedServletContainerFactory factory=getFactory();
  factory.setSessionTimeout(1,TimeUnit.MINUTES);
  assertTimeout(factory,60);
}
