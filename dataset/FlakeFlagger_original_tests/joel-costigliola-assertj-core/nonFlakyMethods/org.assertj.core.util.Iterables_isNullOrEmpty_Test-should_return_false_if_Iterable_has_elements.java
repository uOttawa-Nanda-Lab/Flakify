@Test public void should_return_false_if_Iterable_has_elements(){
  Iterable<String> i=new StringIterable("Frodo");
  assertFalse(Iterables.isNullOrEmpty(i));
}
