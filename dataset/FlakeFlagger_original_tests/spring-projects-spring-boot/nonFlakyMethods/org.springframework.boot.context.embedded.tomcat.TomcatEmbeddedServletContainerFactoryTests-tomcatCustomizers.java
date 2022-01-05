@Test public void tomcatCustomizers() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  TomcatContextCustomizer[] listeners=new TomcatContextCustomizer[4];
  for (int i=0; i < listeners.length; i++) {
    listeners[i]=mock(TomcatContextCustomizer.class);
  }
  factory.setTomcatContextCustomizers(Arrays.asList(listeners[0],listeners[1]));
  factory.addContextCustomizers(listeners[2],listeners[3]);
  this.container=factory.getEmbeddedServletContainer();
  InOrder ordered=inOrder((Object[])listeners);
  for (  TomcatContextCustomizer listener : listeners) {
    ordered.verify(listener).customize((Context)anyObject());
  }
}
