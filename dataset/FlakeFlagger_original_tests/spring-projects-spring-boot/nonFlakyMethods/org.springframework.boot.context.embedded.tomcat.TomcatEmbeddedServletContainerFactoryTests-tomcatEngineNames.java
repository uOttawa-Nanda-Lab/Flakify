@Test public void tomcatEngineNames() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  this.container=factory.getEmbeddedServletContainer();
  factory.setPort(SocketUtils.findAvailableTcpPort(40000));
  TomcatEmbeddedServletContainer container2=(TomcatEmbeddedServletContainer)factory.getEmbeddedServletContainer();
  String firstContainerName=((TomcatEmbeddedServletContainer)this.container).getTomcat().getEngine().getName();
  String secondContainerName=container2.getTomcat().getEngine().getName();
  assertFalse("Tomcat engines must have different names",firstContainerName.equals(secondContainerName));
  container2.stop();
}
