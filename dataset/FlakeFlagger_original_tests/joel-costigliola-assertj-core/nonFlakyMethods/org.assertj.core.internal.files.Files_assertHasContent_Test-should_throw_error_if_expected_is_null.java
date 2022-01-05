@Test public void should_throw_error_if_expected_is_null(){
  thrown.expectNullPointerException("The text to compare to should not be null");
  files.assertHasContent(someInfo(),actual,null,charset);
}
