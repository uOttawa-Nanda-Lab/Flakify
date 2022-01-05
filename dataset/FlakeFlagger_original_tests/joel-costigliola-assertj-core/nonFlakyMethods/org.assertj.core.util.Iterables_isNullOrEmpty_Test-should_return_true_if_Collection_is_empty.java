@Test public void should_return_true_if_Collection_is_empty(){
  Iterable<String> c=new ArrayList<>();
  assertTrue(Iterables.isNullOrEmpty(c));
}
