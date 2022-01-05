@Test public void emptyServerWhenPortIsMinusOne() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setPort(-1);
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  assertThat(this.container.getPort(),lessThan(0));
}
