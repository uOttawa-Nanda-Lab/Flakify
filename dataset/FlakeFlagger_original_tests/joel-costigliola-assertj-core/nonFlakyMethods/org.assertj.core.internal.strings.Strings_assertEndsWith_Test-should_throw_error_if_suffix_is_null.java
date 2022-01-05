@Test public void should_throw_error_if_suffix_is_null(){
  thrown.expectNullPointerException("The given suffix should not be null");
  strings.assertEndsWith(someInfo(),"Yoda",null);
}
