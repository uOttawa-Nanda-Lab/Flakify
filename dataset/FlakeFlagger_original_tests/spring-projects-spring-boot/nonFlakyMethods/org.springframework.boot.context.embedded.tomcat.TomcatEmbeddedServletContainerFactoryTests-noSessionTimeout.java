@Test public void noSessionTimeout() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  factory.setSessionTimeout(0);
  assertTimeout(factory,-1);
}
