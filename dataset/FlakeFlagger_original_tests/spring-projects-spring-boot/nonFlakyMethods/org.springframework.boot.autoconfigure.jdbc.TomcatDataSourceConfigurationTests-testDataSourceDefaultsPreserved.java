@Test public void testDataSourceDefaultsPreserved() throws Exception {
  this.context.register(TomcatDataSourceConfiguration.class);
  this.context.refresh();
  org.apache.tomcat.jdbc.pool.DataSource ds=this.context.getBean(org.apache.tomcat.jdbc.pool.DataSource.class);
  assertEquals(5000,ds.getTimeBetweenEvictionRunsMillis());
  assertEquals(60000,ds.getMinEvictableIdleTimeMillis());
  assertEquals(30000,ds.getMaxWait());
  assertEquals(30000L,ds.getValidationInterval());
}
