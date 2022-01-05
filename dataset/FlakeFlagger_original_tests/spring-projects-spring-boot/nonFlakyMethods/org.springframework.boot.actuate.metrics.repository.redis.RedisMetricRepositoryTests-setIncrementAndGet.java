@Test public void setIncrementAndGet(){
  this.repository.set(new Metric<Number>("foo",12.3));
  this.repository.increment(new Delta<Long>("foo",3L));
  Metric<?> metric=this.repository.findOne("foo");
  assertEquals("foo",metric.getName());
  assertEquals(15.3,metric.getValue().doubleValue(),0.01);
}
