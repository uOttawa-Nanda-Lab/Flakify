@Test public void should_throw_exception_when_canonical_form_representation_fail_for_expected_parent() throws Exception {
  thrown.expect(FilesException.class);
  File expectedParent=mock(File.class);
  when(expectedParent.getCanonicalFile()).thenThrow(new IOException());
  files.assertHasParent(someInfo(),actual,expectedParent);
}
