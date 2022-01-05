@Test public void patternsAcceptedForRegisteredPrefix(){
  this.repository.set("foo.bar","spam");
  Iterator<String> iterator=this.repository.findAllWithPrefix("foo.*").iterator();
  assertEquals("spam",iterator.next());
  assertFalse(iterator.hasNext());
}
