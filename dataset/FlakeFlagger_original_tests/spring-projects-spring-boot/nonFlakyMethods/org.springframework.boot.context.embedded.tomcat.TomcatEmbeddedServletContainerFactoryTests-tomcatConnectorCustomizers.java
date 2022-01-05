@Test public void tomcatConnectorCustomizers() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  TomcatConnectorCustomizer[] listeners=new TomcatConnectorCustomizer[4];
  for (int i=0; i < listeners.length; i++) {
    listeners[i]=mock(TomcatConnectorCustomizer.class);
  }
  factory.setTomcatConnectorCustomizers(Arrays.asList(listeners[0],listeners[1]));
  factory.addConnectorCustomizers(listeners[2],listeners[3]);
  this.container=factory.getEmbeddedServletContainer();
  InOrder ordered=inOrder((Object[])listeners);
  for (  TomcatConnectorCustomizer listener : listeners) {
    ordered.verify(listener).customize((Connector)anyObject());
  }
}
