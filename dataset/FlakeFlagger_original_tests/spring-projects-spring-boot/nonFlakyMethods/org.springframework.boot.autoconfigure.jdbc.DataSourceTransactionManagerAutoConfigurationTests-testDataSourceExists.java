@Test public void testDataSourceExists() throws Exception {
  this.context.register(EmbeddedDataSourceConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  assertNotNull(this.context.getBean(DataSourceTransactionManager.class));
  assertNotNull(this.context.getBean(AbstractTransactionManagementConfiguration.class));
}
