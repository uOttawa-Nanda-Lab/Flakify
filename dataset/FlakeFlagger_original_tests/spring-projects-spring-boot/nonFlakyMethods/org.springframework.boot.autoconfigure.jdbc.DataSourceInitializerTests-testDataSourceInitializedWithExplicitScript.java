@Test public void testDataSourceInitializedWithExplicitScript() throws Exception {
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:true","spring.datasource.schema:" + ClassUtils.addResourcePathToPackagePath(getClass(),"schema.sql"),"spring.datasource.data:" + ClassUtils.addResourcePathToPackagePath(getClass(),"data.sql"));
  this.context.refresh();
  DataSource dataSource=this.context.getBean(DataSource.class);
  assertTrue(dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource);
  assertNotNull(dataSource);
  JdbcOperations template=new JdbcTemplate(dataSource);
  assertEquals(new Integer(1),template.queryForObject("SELECT COUNT(*) from FOO",Integer.class));
}
