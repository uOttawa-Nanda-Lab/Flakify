@Test public void setPredfinedTimer(){
  this.writer.set(new Metric<Number>("timer.foo",200));
  this.writer.set(new Metric<Number>("timer.foo",300));
  assertEquals(2,this.registry.timer("timer.foo").getCount());
}
