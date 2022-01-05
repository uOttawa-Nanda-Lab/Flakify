@Test public void should_return_empty_List_if_given_Collection_is_empty(){
  Collection<String> c=new ArrayList<>();
  assertTrue(Collections.nonNullElementsIn(c).isEmpty());
}
