@Test public void testManualConfiguration() throws Exception {
  this.context.register(SwitchTransactionsOn.class,EmbeddedDataSourceConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  assertNotNull(this.context.getBean(DataSourceTransactionManager.class));
}
