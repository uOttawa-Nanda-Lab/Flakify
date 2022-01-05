@Test public void incrementCounter(){
  this.writer.increment(new Delta<Number>("foo",2));
  this.writer.increment(new Delta<Number>("foo",1));
  assertEquals(3,this.registry.counter("foo").getCount());
}
