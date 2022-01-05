@Test public void testDataSourceInitializedWithMultipleScripts() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:true","spring.datasource.schema:" + ClassUtils.addResourcePathToPackagePath(getClass(),"schema.sql") + ","+ ClassUtils.addResourcePathToPackagePath(getClass(),"another.sql"),"spring.datasource.data:" + ClassUtils.addResourcePathToPackagePath(getClass(),"data.sql"));
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  DataSource dataSource=this.context.getBean(DataSource.class);
  assertTrue(dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource);
  assertNotNull(dataSource);
  JdbcOperations template=new JdbcTemplate(dataSource);
  assertEquals(new Integer(1),template.queryForObject("SELECT COUNT(*) from FOO",Integer.class));
  assertEquals(new Integer(0),template.queryForObject("SELECT COUNT(*) from SPAM",Integer.class));
}
