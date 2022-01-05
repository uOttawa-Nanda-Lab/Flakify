@Test public void stopServlet() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  int port=this.container.getPort();
  this.container.stop();
  this.thrown.expect(IOException.class);
  String response=getResponse(getLocalUrl(port,"/hello"));
  throw new RuntimeException("Unexpected response on port " + port + " : "+ response);
}
