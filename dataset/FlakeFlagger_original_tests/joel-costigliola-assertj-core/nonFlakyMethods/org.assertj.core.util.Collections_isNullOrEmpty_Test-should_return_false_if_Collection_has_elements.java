@Test public void should_return_false_if_Collection_has_elements(){
  Collection<String> c=newArrayList("Frodo");
  assertFalse(Collections.isNullOrEmpty(c));
}
