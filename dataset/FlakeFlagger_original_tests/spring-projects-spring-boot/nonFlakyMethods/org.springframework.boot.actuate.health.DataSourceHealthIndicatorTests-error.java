@Test public void error(){
  this.indicator.setDataSource(this.dataSource);
  this.indicator.setQuery("SELECT COUNT(*) from BAR");
  Health health=this.indicator.health();
  assertThat(health.getDetails().get("database"),notNullValue());
  assertEquals(Status.DOWN,health.getStatus());
}
