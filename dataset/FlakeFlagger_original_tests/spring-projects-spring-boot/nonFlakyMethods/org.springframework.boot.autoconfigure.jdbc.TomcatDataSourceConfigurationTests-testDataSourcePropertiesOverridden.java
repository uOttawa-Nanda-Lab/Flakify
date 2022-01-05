@Test public void testDataSourcePropertiesOverridden() throws Exception {
  this.context.register(TomcatDataSourceConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "url:jdbc:foo//bar/spam");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "testWhileIdle:true");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "testOnBorrow:true");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "testOnReturn:true");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "timeBetweenEvictionRunsMillis:10000");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "minEvictableIdleTimeMillis:12345");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "maxWait:1234");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "jdbcInterceptors:SlowQueryReport");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "validationInterval:9999");
  this.context.refresh();
  org.apache.tomcat.jdbc.pool.DataSource ds=this.context.getBean(org.apache.tomcat.jdbc.pool.DataSource.class);
  assertEquals("jdbc:foo//bar/spam",ds.getUrl());
  assertTrue(ds.isTestWhileIdle());
  assertTrue(ds.isTestOnBorrow());
  assertTrue(ds.isTestOnReturn());
  assertEquals(10000,ds.getTimeBetweenEvictionRunsMillis());
  assertEquals(12345,ds.getMinEvictableIdleTimeMillis());
  assertEquals(1234,ds.getMaxWait());
  assertEquals(9999L,ds.getValidationInterval());
  assertDataSourceHasInterceptors(ds);
}
