@Test public void additionalConnectorPortClashThrowsIllegalStateException() throws InterruptedException, UnknownHostException, IOException {
  final int port=SocketUtils.findAvailableTcpPort(40000);
  doWithBlockedPort(port,new Runnable(){
    @Override public void run(){
      TomcatEmbeddedServletContainerFactory factory=getFactory();
      Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
      connector.setPort(port);
      factory.addAdditionalTomcatConnectors(connector);
      try {
        TomcatEmbeddedServletContainerFactoryTests.this.container=factory.getEmbeddedServletContainer();
        TomcatEmbeddedServletContainerFactoryTests.this.container.start();
        fail();
      }
 catch (      IllegalStateException ex) {
      }
    }
  }
);
}
