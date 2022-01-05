@Test public void testHikariIsFallback() throws Exception {
  HikariDataSource pool=testDataSourceFallback(HikariDataSource.class,"org.apache.tomcat");
  assertEquals("jdbc:hsqldb:mem:testdb",pool.getJdbcUrl());
}
