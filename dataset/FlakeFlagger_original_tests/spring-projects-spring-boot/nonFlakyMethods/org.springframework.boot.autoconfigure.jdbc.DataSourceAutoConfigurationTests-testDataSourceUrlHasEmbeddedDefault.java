@Test public void testDataSourceUrlHasEmbeddedDefault() throws Exception {
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  org.apache.tomcat.jdbc.pool.DataSource dataSource=this.context.getBean(org.apache.tomcat.jdbc.pool.DataSource.class);
  assertNotNull(dataSource.getUrl());
  assertNotNull(dataSource.getDriverClassName());
}
