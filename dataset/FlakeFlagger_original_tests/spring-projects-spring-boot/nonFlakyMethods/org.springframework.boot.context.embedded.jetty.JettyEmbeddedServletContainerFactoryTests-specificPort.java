@Test public void specificPort() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  int specificPort=SocketUtils.findAvailableTcpPort(40000);
  factory.setPort(specificPort);
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  assertThat(getResponse("http://localhost:" + specificPort + "/hello"),equalTo("Hello World"));
  assertEquals(specificPort,this.container.getPort());
}
