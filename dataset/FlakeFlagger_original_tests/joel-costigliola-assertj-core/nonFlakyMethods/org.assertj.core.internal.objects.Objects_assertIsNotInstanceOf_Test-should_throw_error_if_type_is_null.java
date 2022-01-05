@Test public void should_throw_error_if_type_is_null(){
  thrown.expectNullPointerException("The given type should not be null");
  objects.assertIsNotInstanceOf(someInfo(),actual,null);
}
