@Test public void specificContextRoot() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setContextPath("/say");
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  assertThat(getResponse(getLocalUrl("/say/hello")),equalTo("Hello World"));
}
