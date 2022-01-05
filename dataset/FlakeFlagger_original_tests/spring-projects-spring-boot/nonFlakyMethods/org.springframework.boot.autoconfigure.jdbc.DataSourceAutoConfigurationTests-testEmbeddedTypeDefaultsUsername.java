@Test public void testEmbeddedTypeDefaultsUsername() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.driverClassName:org.hsqldb.jdbcDriver","spring.datasource.url:jdbc:hsqldb:mem:testdb");
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  DataSource bean=this.context.getBean(DataSource.class);
  assertNotNull(bean);
  org.apache.tomcat.jdbc.pool.DataSource pool=(org.apache.tomcat.jdbc.pool.DataSource)bean;
  assertEquals("org.hsqldb.jdbcDriver",pool.getDriverClassName());
  assertEquals("sa",pool.getUsername());
}
