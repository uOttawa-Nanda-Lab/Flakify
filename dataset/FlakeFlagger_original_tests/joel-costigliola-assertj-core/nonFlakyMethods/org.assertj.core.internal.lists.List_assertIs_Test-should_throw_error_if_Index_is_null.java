@Test public void should_throw_error_if_Index_is_null(){
  thrown.expectNullPointerException("Index should not be null");
  lists.assertIs(someInfo(),actual,condition,null);
}
