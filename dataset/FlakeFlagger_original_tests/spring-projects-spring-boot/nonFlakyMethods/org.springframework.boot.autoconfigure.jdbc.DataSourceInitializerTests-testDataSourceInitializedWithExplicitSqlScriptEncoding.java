@Test public void testDataSourceInitializedWithExplicitSqlScriptEncoding() throws Exception {
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:true","spring.datasource.sqlScriptEncoding:UTF-8","spring.datasource.schema:" + ClassUtils.addResourcePathToPackagePath(getClass(),"encoding-schema.sql"),"spring.datasource.data:" + ClassUtils.addResourcePathToPackagePath(getClass(),"encoding-data.sql"));
  this.context.refresh();
  DataSource dataSource=this.context.getBean(DataSource.class);
  assertTrue(dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource);
  assertNotNull(dataSource);
  JdbcOperations template=new JdbcTemplate(dataSource);
  assertEquals(new Integer(2),template.queryForObject("SELECT COUNT(*) from BAR",Integer.class));
  assertEquals("bar",template.queryForObject("SELECT name from BAR WHERE id=1",String.class));
  assertEquals("ばー",template.queryForObject("SELECT name from BAR WHERE id=2",String.class));
}
