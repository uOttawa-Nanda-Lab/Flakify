@Test public void errorPage() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/hello"));
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration(),errorServletRegistration());
  this.container.start();
  assertThat(getResponse(getLocalUrl("/hello")),equalTo("Hello World"));
  assertThat(getResponse(getLocalUrl("/bang")),equalTo("Hello World"));
}
