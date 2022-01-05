@Test public void should_throw_error_if_array_of_types_is_empty(){
  thrown.expectIllegalArgumentException("The given array of types should not be empty");
  objects.assertIsNotInstanceOfAny(someInfo(),actual,new Class<?>[0]);
}
