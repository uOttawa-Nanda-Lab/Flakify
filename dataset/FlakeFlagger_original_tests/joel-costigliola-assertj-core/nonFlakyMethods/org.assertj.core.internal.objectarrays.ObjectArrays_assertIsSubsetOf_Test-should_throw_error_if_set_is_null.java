@Test public void should_throw_error_if_set_is_null(){
  actual=array("Yoda","Luke");
  thrown.expectNullPointerException(iterableToLookForIsNull());
  arrays.assertIsSubsetOf(someInfo(),actual,null);
}
