@Test public void count(){
  this.repository.increment(new Delta<Long>("foo",3L));
  this.repository.set(new Metric<Number>("bar",12.3));
  assertEquals(2,this.repository.count());
}
