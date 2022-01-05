@Test public void should_throw_error_wrapping_catched_IOException() throws IOException {
  IOException cause=new IOException();
  when(diff.diff(path.toFile(),expected,charset)).thenThrow(cause);
  when(nioFilesWrapper.exists(path)).thenReturn(true);
  when(nioFilesWrapper.isReadable(path)).thenReturn(true);
  try {
    paths.assertHasContent(someInfo(),path,expected,charset);
    failBecauseExceptionWasNotThrown(FilesException.class);
  }
 catch (  FilesException e) {
    assertThat(e.getCause()).isSameAs(cause);
  }
}
