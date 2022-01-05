@Test public void doubleStop() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  this.container.stop();
  this.container.stop();
}
