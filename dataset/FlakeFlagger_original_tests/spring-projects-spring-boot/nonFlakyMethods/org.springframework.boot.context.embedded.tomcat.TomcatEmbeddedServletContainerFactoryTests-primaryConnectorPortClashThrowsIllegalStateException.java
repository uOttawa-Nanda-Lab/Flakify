@Test public void primaryConnectorPortClashThrowsIllegalStateException() throws InterruptedException, UnknownHostException, IOException {
  final int port=SocketUtils.findAvailableTcpPort(40000);
  doWithBlockedPort(port,new Runnable(){
    @Override public void run(){
      TomcatEmbeddedServletContainerFactory factory=getFactory();
      factory.setPort(port);
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
