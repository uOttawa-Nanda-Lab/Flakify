@Test public void should_throw_error_if_Iterable_is_empty(){
  thrown.expectIllegalArgumentException(ErrorMessages.iterableIsEmpty());
  objects.assertIsIn(someInfo(),"Yoda",emptyList());
}
