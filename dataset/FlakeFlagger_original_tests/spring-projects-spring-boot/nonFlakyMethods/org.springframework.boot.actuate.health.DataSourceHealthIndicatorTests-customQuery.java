@Test public void customQuery(){
  this.indicator.setDataSource(this.dataSource);
  new JdbcTemplate(this.dataSource).execute("CREATE TABLE FOO (id INTEGER IDENTITY PRIMARY KEY)");
  this.indicator.setQuery("SELECT COUNT(*) from FOO");
  Health health=this.indicator.health();
  System.err.println(health);
  assertNotNull(health.getDetails().get("database"));
  assertEquals(Status.UP,health.getStatus());
  assertNotNull(health.getDetails().get("hello"));
}
