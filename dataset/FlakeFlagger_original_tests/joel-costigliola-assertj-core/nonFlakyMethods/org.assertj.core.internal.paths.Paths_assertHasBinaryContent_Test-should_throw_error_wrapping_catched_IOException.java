@Test public void should_throw_error_wrapping_catched_IOException() throws IOException {
  IOException cause=new IOException();
  when(binaryDiff.diff(path.toFile(),expected)).thenThrow(cause);
  when(nioFilesWrapper.exists(path)).thenReturn(true);
  when(nioFilesWrapper.isReadable(path)).thenReturn(true);
  try {
    paths.assertHasBinaryContent(someInfo(),path,expected);
    failBecauseExceptionWasNotThrown(FilesException.class);
  }
 catch (  FilesException e) {
    assertThat(e.getCause()).isSameAs(cause);
  }
}
