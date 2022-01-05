@Test public void testDataSourceExists() throws Exception {
  this.context.register(TomcatDataSourceConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  assertNotNull(this.context.getBean(org.apache.tomcat.jdbc.pool.DataSource.class));
}
