@Test public void multipleConfigurations() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  ServletContextInitializer[] initializers=new ServletContextInitializer[6];
  for (int i=0; i < initializers.length; i++) {
    initializers[i]=mock(ServletContextInitializer.class);
  }
  factory.setInitializers(Arrays.asList(initializers[2],initializers[3]));
  factory.addInitializers(initializers[4],initializers[5]);
  this.container=factory.getEmbeddedServletContainer(initializers[0],initializers[1]);
  this.container.start();
  InOrder ordered=inOrder((Object[])initializers);
  for (  ServletContextInitializer initializer : initializers) {
    ordered.verify(initializer).onStartup((ServletContext)anyObject());
  }
}
