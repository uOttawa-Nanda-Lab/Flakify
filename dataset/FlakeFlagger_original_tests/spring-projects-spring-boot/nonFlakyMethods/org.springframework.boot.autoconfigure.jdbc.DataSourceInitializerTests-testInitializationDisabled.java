@Test public void testInitializationDisabled() throws Exception {
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  DataSource dataSource=this.context.getBean(DataSource.class);
  this.context.publishEvent(new DataSourceInitializedEvent(dataSource));
  assertTrue(dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource);
  assertNotNull(dataSource);
  JdbcOperations template=new JdbcTemplate(dataSource);
  try {
    template.queryForObject("SELECT COUNT(*) from BAR",Integer.class);
    fail("Query should have failed as BAR table does not exist");
  }
 catch (  BadSqlGrammarException ex) {
    SQLException sqlException=ex.getSQLException();
    int expectedCode=-5501;
    assertEquals(expectedCode,sqlException.getErrorCode());
  }
}
