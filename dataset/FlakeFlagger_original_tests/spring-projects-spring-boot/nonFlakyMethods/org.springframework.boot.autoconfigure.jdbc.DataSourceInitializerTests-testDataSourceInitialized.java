@Test public void testDataSourceInitialized() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:true");
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  DataSource dataSource=this.context.getBean(DataSource.class);
  assertTrue(dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource);
  assertNotNull(dataSource);
  JdbcOperations template=new JdbcTemplate(dataSource);
  assertEquals(new Integer(1),template.queryForObject("SELECT COUNT(*) from BAR",Integer.class));
}
