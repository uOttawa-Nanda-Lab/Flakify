@Test public void startServletAndFilter() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration(),new FilterRegistrationBean(new ExampleFilter()));
  this.container.start();
  assertThat(getResponse(getLocalUrl("/hello")),equalTo("[Hello World]"));
}
