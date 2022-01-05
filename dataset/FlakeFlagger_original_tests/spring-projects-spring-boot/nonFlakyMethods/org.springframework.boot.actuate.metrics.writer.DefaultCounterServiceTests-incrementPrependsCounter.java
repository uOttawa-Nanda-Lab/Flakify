@Test public void incrementPrependsCounter(){
  this.service.increment("foo");
  verify(this.repository).increment(this.captor.capture());
  assertEquals("counter.foo",this.captor.getValue().getName());
  assertEquals(1L,this.captor.getValue().getValue());
}
