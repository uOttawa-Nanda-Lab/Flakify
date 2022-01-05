@Test public void containerEventPublished() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  this.context.registerBeanDefinition("listener",new RootBeanDefinition(MockListener.class));
  this.context.refresh();
  EmbeddedServletContainerInitializedEvent event=this.context.getBean(MockListener.class).getEvent();
  assertNotNull(event);
  assertTrue(event.getSource().getPort() >= 0);
  assertEquals(this.context,event.getApplicationContext());
}
