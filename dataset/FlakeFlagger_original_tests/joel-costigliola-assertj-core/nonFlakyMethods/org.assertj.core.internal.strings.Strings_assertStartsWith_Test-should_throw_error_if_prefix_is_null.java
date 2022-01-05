@Test public void should_throw_error_if_prefix_is_null(){
  thrown.expectNullPointerException("The given prefix should not be null");
  strings.assertStartsWith(someInfo(),"Yoda",null);
}
