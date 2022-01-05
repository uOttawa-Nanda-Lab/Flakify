@Test public void testDataSourcePropertiesOverridden() throws Exception {
  this.context.register(CommonsDataSourceConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "url:jdbc:foo//bar/spam");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "testWhileIdle:true");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "testOnBorrow:true");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "testOnReturn:true");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "timeBetweenEvictionRunsMillis:10000");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "minEvictableIdleTimeMillis:12345");
  EnvironmentTestUtils.addEnvironment(this.context,PREFIX + "maxWait:1234");
  this.context.refresh();
  BasicDataSource ds=this.context.getBean(BasicDataSource.class);
  assertEquals("jdbc:foo//bar/spam",ds.getUrl());
  assertTrue(ds.getTestWhileIdle());
  assertTrue(ds.getTestOnBorrow());
  assertTrue(ds.getTestOnReturn());
  assertEquals(10000,ds.getTimeBetweenEvictionRunsMillis());
  assertEquals(12345,ds.getMinEvictableIdleTimeMillis());
  assertEquals(1234,ds.getMaxWait());
}
