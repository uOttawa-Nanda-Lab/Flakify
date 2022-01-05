@Test public void jettyConfigurations() throws Exception {
  JettyEmbeddedServletContainerFactory factory=getFactory();
  Configuration[] configurations=new Configuration[4];
  for (int i=0; i < configurations.length; i++) {
    configurations[i]=mock(Configuration.class);
  }
  factory.setConfigurations(Arrays.asList(configurations[0],configurations[1]));
  factory.addConfigurations(configurations[2],configurations[3]);
  this.container=factory.getEmbeddedServletContainer();
  InOrder ordered=inOrder((Object[])configurations);
  for (  Configuration configuration : configurations) {
    ordered.verify(configuration).configure((WebAppContext)anyObject());
  }
}
