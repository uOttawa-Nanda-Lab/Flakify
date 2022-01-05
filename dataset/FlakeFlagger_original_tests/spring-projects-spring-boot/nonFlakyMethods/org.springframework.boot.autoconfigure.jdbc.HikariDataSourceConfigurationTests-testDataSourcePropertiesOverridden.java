@Test public void testDataSourcePropertiesOverridden() throws Exception {
  this.context.register(HikariDataSourceConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "jdbcUrl:jdbc:foo//bar/spam");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "maxLifetime:1234");
  this.context.refresh();
  HikariDataSource ds=this.context.getBean(HikariDataSource.class);
  assertEquals("jdbc:foo//bar/spam",ds.getJdbcUrl());
  assertEquals(1234,ds.getMaxLifetime());
}
