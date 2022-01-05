@Test public void should_return_empty_List_if_given_Iterable_is_null(){
  Collection<?> c=null;
  assertTrue(Iterables.nonNullElementsIn(c).isEmpty());
}
