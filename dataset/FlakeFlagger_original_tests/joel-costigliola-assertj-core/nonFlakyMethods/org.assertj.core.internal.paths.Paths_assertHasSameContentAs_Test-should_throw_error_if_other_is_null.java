@Test public void should_throw_error_if_other_is_null(){
  thrown.expectNullPointerException("The given Path to compare actual content to should not be null");
  paths.assertHasSameContentAs(someInfo(),actual,null);
}
