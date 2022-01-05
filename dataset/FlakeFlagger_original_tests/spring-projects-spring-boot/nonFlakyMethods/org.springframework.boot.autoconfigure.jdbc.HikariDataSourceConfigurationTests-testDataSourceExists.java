@Test public void testDataSourceExists() throws Exception {
  this.context.register(HikariDataSourceConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  assertNotNull(this.context.getBean(HikariDataSource.class));
}
