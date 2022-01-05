@Test public void noDataSource(){
  load();
  assertEquals(0,this.context.getBeansOfType(PublicMetrics.class).size());
}
