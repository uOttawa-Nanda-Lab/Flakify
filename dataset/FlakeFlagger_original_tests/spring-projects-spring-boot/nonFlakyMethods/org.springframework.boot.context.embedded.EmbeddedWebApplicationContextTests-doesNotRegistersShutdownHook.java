@Test public void doesNotRegistersShutdownHook() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  this.context.refresh();
  Field shutdownHookField=AbstractApplicationContext.class.getDeclaredField("shutdownHook");
  shutdownHookField.setAccessible(true);
  Object shutdownHook=shutdownHookField.get(this.context);
  assertThat(shutdownHook,nullValue());
}
