@Test public void should_throw_exception_when_canonical_form_representation_fail() throws Exception {
  thrown.expect(FilesException.class);
  File actual=mock(File.class);
  File expectedParent=mock(File.class);
  when(actual.getParentFile()).thenReturn(expectedParent);
  when(expectedParent.getCanonicalFile()).thenThrow(new IOException());
  files.assertHasParent(someInfo(),actual,expectedParent);
}
