@Test public void should_throw_npe_if_extension_is_null() throws Exception {
  thrown.expectNullPointerException("The expected extension should not be null.");
  files.assertHasExtension(someInfo(),actual,null);
}
