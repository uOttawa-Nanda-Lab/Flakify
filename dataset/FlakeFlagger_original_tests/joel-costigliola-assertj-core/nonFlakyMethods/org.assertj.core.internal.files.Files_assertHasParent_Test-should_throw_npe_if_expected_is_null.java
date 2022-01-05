@Test public void should_throw_npe_if_expected_is_null() throws Exception {
  thrown.expectNullPointerException("The expected parent file should not be null.");
  files.assertHasParent(someInfo(),actual,null);
}
