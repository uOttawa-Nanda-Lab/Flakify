@Test public void tomcatListeners() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  LifecycleListener[] listeners=new LifecycleListener[4];
  for (int i=0; i < listeners.length; i++) {
    listeners[i]=mock(LifecycleListener.class);
  }
  factory.setContextLifecycleListeners(Arrays.asList(listeners[0],listeners[1]));
  factory.addContextLifecycleListeners(listeners[2],listeners[3]);
  this.container=factory.getEmbeddedServletContainer();
  InOrder ordered=inOrder((Object[])listeners);
  for (  LifecycleListener listener : listeners) {
    ordered.verify(listener).lifecycleEvent((LifecycleEvent)anyObject());
  }
}
