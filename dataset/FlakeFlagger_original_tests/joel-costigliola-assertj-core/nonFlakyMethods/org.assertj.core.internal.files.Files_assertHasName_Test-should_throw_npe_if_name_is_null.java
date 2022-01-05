@Test public void should_throw_npe_if_name_is_null() throws Exception {
  thrown.expectNullPointerException("The expected name should not be null.");
  files.assertHasName(someInfo(),actual,null);
}
