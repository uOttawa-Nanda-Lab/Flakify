@Test public void tomcatAdditionalConnectors() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  Connector[] listeners=new Connector[4];
  for (int i=0; i < listeners.length; i++) {
    Connector connector=mock(Connector.class);
    given(connector.getState()).willReturn(LifecycleState.STOPPED);
    listeners[i]=connector;
  }
  factory.addAdditionalTomcatConnectors(listeners);
  this.container=factory.getEmbeddedServletContainer();
  Map<Service,Connector[]> connectors=((TomcatEmbeddedServletContainer)this.container).getServiceConnectors();
  assertThat(connectors.values().iterator().next().length,equalTo(listeners.length + 1));
}
