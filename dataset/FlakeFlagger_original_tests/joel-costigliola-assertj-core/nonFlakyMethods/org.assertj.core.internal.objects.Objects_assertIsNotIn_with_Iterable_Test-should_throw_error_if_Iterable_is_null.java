@Test public void should_throw_error_if_Iterable_is_null(){
  thrown.expectNullPointerException(iterableIsNull());
  Iterable<String> c=null;
  objects.assertIsNotIn(someInfo(),"Luke",c);
}
