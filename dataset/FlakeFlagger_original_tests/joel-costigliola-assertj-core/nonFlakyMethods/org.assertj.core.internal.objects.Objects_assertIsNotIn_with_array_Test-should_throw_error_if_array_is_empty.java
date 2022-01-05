@Test public void should_throw_error_if_array_is_empty(){
  thrown.expectIllegalArgumentException(arrayIsEmpty());
  objects.assertIsNotIn(someInfo(),"Yoda",emptyArray());
}
