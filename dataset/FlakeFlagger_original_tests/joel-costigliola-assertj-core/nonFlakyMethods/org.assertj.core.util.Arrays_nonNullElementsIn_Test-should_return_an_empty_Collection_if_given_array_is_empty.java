@Test public void should_return_an_empty_Collection_if_given_array_is_empty(){
  String[] array=new String[0];
  assertTrue(Arrays.nonNullElementsIn(array).isEmpty());
}
