@Test public void cannotSecondRefresh() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  this.context.refresh();
  this.thrown.expect(IllegalStateException.class);
  this.context.refresh();
}
