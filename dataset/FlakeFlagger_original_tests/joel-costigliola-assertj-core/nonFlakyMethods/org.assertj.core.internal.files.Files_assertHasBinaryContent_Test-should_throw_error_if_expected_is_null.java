@Test public void should_throw_error_if_expected_is_null(){
  thrown.expectNullPointerException("The binary content to compare to should not be null");
  files.assertHasBinaryContent(someInfo(),actual,null);
}
