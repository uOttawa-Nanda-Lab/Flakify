@Test public void should_return_true_if_Collection_is_empty(){
  Collection<String> c=new ArrayList<>();
  assertTrue(Collections.isNullOrEmpty(c));
}
