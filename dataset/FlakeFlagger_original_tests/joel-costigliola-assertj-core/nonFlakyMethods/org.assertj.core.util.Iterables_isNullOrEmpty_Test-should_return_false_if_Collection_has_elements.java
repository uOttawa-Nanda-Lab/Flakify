@Test public void should_return_false_if_Collection_has_elements(){
  Iterable<String> c=newArrayList("Frodo");
  assertFalse(Iterables.isNullOrEmpty(c));
}
