@Test public void stopOnClose() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  this.context.refresh();
  MockEmbeddedServletContainerFactory escf=getEmbeddedServletContainerFactory();
  this.context.close();
  verify(escf.getContainer()).stop();
}
