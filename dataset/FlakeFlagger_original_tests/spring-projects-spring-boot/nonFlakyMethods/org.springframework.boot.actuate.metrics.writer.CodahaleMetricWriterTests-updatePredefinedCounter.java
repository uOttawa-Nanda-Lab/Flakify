@Test public void updatePredefinedCounter(){
  this.writer.increment(new Delta<Number>("counter.foo",2));
  this.writer.increment(new Delta<Number>("counter.foo",1));
  assertEquals(3,this.registry.counter("counter.foo").getCount());
}
