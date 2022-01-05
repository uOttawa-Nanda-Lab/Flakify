@Test public void should_throw_error_if_array_is_null(){
  thrown.expectNullPointerException(arrayIsNull());
  Object[] array=null;
  objects.assertIsNotIn(someInfo(),"Yoda",array);
}
