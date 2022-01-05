@Test public void startServlet() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  assertThat(getResponse(getLocalUrl("/hello")),equalTo("Hello World"));
}
