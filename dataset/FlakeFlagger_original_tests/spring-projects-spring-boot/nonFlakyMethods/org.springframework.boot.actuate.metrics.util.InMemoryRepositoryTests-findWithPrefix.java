@Test public void findWithPrefix(){
  this.repository.set("foo","bar");
  this.repository.set("foo.bar","one");
  this.repository.set("foo.min","two");
  this.repository.set("foo.max","three");
  assertEquals(3,((Collection<?>)this.repository.findAllWithPrefix("foo")).size());
}
