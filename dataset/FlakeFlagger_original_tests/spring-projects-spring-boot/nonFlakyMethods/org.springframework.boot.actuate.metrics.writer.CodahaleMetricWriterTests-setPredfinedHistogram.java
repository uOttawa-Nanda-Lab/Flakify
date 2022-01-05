@Test public void setPredfinedHistogram(){
  this.writer.set(new Metric<Number>("histogram.foo",2.1));
  this.writer.set(new Metric<Number>("histogram.foo",2.3));
  assertEquals(2,this.registry.histogram("histogram.foo").getCount());
}
