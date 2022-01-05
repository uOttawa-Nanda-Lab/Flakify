@Test public void database(){
  this.indicator.setDataSource(this.dataSource);
  Health health=this.indicator.health();
  assertNotNull(health.getDetails().get("database"));
  assertNotNull(health.getDetails().get("hello"));
}
