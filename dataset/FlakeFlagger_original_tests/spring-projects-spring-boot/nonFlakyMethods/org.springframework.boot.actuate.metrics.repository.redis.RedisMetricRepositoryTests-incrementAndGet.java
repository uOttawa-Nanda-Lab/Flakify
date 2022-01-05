@Test public void incrementAndGet(){
  this.repository.increment(new Delta<Long>("foo",3L));
  assertEquals(3,this.repository.findOne("foo").getValue().longValue());
}
