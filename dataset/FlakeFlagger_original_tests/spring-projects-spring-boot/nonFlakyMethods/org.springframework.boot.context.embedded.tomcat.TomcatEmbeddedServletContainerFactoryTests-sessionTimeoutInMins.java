@Test public void sessionTimeoutInMins() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  factory.setSessionTimeout(1,TimeUnit.MINUTES);
  assertTimeout(factory,1);
}
