@Test public void setAndGet(){
  this.repository.set(new Metric<Number>("foo",12.3));
  Metric<?> metric=this.repository.findOne("foo");
  assertEquals("foo",metric.getName());
  assertEquals(12.3,metric.getValue().doubleValue(),0.01);
}
