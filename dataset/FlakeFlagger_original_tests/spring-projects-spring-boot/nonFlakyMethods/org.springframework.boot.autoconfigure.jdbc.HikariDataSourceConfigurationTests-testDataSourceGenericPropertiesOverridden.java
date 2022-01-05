@Test public void testDataSourceGenericPropertiesOverridden() throws Exception {
  this.context.register(HikariDataSourceConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "dataSourceProperties.dataSourceClassName:org.h2.JDBCDataSource");
  this.context.refresh();
  HikariDataSource ds=this.context.getBean(HikariDataSource.class);
  assertEquals("org.h2.JDBCDataSource",ds.getDataSourceProperties().getProperty("dataSourceClassName"));
}
