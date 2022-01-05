@Test public void should_throw_error_if_expected_is_null(){
  thrown.expectNullPointerException("The file to compare to should not be null");
  files.assertSameContentAs(someInfo(),actual,null);
}
