@Test public void testDataSourceDefaultsPreserved() throws Exception {
  this.context.register(HikariDataSourceConfiguration.class);
  this.context.refresh();
  HikariDataSource ds=this.context.getBean(HikariDataSource.class);
  assertEquals(1800000,ds.getMaxLifetime());
}
