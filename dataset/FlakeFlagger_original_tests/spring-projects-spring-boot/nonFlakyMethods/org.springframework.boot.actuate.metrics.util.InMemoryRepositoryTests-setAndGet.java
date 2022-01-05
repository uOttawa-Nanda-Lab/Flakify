@Test public void setAndGet(){
  this.repository.set("foo","bar");
  assertEquals("bar",this.repository.findOne("foo"));
}
