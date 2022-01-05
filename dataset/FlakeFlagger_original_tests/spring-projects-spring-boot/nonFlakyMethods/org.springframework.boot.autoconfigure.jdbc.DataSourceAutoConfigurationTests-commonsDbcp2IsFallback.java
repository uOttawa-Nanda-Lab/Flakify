@Test public void commonsDbcp2IsFallback() throws Exception {
  org.apache.commons.dbcp2.BasicDataSource dataSource=testDataSourceFallback(org.apache.commons.dbcp2.BasicDataSource.class,"org.apache.tomcat","com.zaxxer.hikari","org.apache.commons.dbcp.");
  assertEquals("jdbc:hsqldb:mem:testdb",dataSource.getUrl());
}
