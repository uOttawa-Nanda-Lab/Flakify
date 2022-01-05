@Test public void should_pass_if_path_has_same_content_as_other() throws IOException {
  when(diff.diff(actual.toFile(),other.toFile())).thenReturn(new ArrayList<String>());
  when(nioFilesWrapper.exists(actual)).thenReturn(true);
  when(nioFilesWrapper.isReadable(actual)).thenReturn(true);
  when(nioFilesWrapper.isReadable(other)).thenReturn(true);
  paths.assertHasSameContentAs(someInfo(),actual,other);
}
