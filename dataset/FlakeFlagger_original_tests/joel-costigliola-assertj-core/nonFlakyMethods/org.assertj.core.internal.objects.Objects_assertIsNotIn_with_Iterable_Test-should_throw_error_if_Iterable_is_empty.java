@Test public void should_throw_error_if_Iterable_is_empty(){
  thrown.expectIllegalArgumentException(iterableIsEmpty());
  objects.assertIsNotIn(someInfo(),"Luke",emptyList());
}
