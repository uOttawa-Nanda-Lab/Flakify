@Test public void should_throw_error_if_type_is_null(){
  thrown.expectNullPointerException("The given types should not be null");
  objects.assertIsNotOfAnyClassIn(someInfo(),actual,null);
}
