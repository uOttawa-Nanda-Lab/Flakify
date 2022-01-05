@Test public void commonsDbcpIsFallback() throws Exception {
  BasicDataSource dataSource=testDataSourceFallback(BasicDataSource.class,"org.apache.tomcat","com.zaxxer.hikari");
  assertEquals("jdbc:hsqldb:mem:testdb",dataSource.getUrl());
}
