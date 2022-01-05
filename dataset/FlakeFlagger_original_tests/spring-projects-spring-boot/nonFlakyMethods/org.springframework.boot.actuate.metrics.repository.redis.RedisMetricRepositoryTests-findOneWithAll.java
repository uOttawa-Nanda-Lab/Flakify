@Test public void findOneWithAll(){
  this.repository.increment(new Delta<Long>("foo",3L));
  Metric<?> metric=this.repository.findAll().iterator().next();
  assertEquals("foo",metric.getName());
}
