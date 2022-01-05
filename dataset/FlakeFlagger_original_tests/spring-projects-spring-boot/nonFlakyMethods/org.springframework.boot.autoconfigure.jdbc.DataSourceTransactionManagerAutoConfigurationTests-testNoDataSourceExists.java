@Test public void testNoDataSourceExists() throws Exception {
  this.context.register(DataSourceTransactionManagerAutoConfiguration.class);
  this.context.refresh();
  assertEquals(0,this.context.getBeanNamesForType(DataSource.class).length);
  assertEquals(0,this.context.getBeanNamesForType(DataSourceTransactionManager.class).length);
}
