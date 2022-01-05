@Test public void valve() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  Valve valve=mock(Valve.class);
  factory.addContextValves(valve);
  this.container=factory.getEmbeddedServletContainer();
  verify(valve).setNext(any(Valve.class));
}
