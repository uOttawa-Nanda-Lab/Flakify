@Test public void should_throw_error_if_actual_is_null(){
  actual=null;
  thrown.expectAssertionError(actualIsNull());
  arrays.assertIsSubsetOf(someInfo(),actual,newArrayList());
}
