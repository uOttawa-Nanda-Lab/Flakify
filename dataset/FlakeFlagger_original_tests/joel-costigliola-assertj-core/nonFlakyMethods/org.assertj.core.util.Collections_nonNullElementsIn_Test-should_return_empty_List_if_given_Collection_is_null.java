@Test public void should_return_empty_List_if_given_Collection_is_null(){
  Collection<?> c=null;
  assertTrue(Collections.nonNullElementsIn(c).isEmpty());
}
