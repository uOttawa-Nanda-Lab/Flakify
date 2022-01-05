@Test public void jettyCustomizations() throws Exception {
  JettyEmbeddedServletContainerFactory factory=getFactory();
  JettyServerCustomizer[] configurations=new JettyServerCustomizer[4];
  for (int i=0; i < configurations.length; i++) {
    configurations[i]=mock(JettyServerCustomizer.class);
  }
  factory.setServerCustomizers(Arrays.asList(configurations[0],configurations[1]));
  factory.addServerCustomizers(configurations[2],configurations[3]);
  this.container=factory.getEmbeddedServletContainer();
  InOrder ordered=inOrder((Object[])configurations);
  for (  JettyServerCustomizer configuration : configurations) {
    ordered.verify(configuration).customize((Server)anyObject());
  }
}
