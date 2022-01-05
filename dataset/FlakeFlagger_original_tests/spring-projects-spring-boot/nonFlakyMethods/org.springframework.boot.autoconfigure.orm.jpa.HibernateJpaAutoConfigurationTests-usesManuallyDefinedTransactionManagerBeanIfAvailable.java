@Test public void usesManuallyDefinedTransactionManagerBeanIfAvailable(){
  setupTestConfiguration(TestConfigurationWithTransactionManager.class);
  this.context.refresh();
  PlatformTransactionManager txManager=this.context.getBean(PlatformTransactionManager.class);
  assertThat(txManager,instanceOf(CustomJpaTransactionManager.class));
}
