@Test public void testDataSourceTransactionManagerNotCreated() throws Exception {
  this.context.register(DataSourceTransactionManagerAutoConfiguration.class);
  setupTestConfiguration();
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  assertTrue(this.context.getBean("transactionManager") instanceof JpaTransactionManager);
}
