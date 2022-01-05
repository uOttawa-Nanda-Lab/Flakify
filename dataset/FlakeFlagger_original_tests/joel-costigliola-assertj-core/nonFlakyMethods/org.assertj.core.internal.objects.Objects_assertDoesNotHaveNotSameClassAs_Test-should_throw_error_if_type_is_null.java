@Test public void should_throw_error_if_type_is_null(){
  thrown.expectNullPointerException("The given object should not be null");
  objects.assertDoesNotHaveSameClassAs(someInfo(),actual,null);
}
