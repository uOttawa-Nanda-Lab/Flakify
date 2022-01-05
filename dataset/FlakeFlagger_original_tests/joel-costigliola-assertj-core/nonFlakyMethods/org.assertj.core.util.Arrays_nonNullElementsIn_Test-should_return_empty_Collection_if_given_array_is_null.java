@Test public void should_return_empty_Collection_if_given_array_is_null(){
  assertTrue(Arrays.nonNullElementsIn(null).isEmpty());
}
