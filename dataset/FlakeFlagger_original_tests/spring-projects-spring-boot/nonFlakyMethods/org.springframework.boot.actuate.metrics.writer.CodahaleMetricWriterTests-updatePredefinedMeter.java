@Test public void updatePredefinedMeter(){
  this.writer.increment(new Delta<Number>("meter.foo",2));
  this.writer.increment(new Delta<Number>("meter.foo",1));
  assertEquals(3,this.registry.meter("meter.foo").getCount());
}
