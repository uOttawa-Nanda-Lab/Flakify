@Test public void should_throw_error_wrapping_catched_IOException() throws IOException {
  IOException cause=new IOException();
  when(diff.diff(actual.toFile(),other.toFile())).thenThrow(cause);
  when(nioFilesWrapper.exists(actual)).thenReturn(true);
  when(nioFilesWrapper.isReadable(actual)).thenReturn(true);
  when(nioFilesWrapper.isReadable(other)).thenReturn(true);
  try {
    paths.assertHasSameContentAs(someInfo(),actual,other);
    failBecauseExceptionWasNotThrown(FilesException.class);
  }
 catch (  FilesException e) {
    assertThat(e.getCause()).isSameAs(cause);
  }
}
