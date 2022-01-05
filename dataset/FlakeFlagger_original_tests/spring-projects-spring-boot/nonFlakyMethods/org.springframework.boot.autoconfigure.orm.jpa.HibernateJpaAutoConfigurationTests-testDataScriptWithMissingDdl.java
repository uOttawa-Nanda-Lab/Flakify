@Test public void testDataScriptWithMissingDdl() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.data:classpath:/city.sql","spring.datasource.schema:classpath:/ddl.sql");
  setupTestConfiguration();
  this.context.refresh();
  assertEquals(new Integer(1),new JdbcTemplate(this.context.getBean(DataSource.class)).queryForObject("SELECT COUNT(*) from CITY",Integer.class));
}
