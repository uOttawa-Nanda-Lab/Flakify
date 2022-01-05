@Test public void should_throw_error_if_expected_is_null(){
  thrown.expectNullPointerException("The InputStream to compare to should not be null");
  inputStreams.assertSameContentAs(someInfo(),actual,null);
}
