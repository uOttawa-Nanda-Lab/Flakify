@Test public void should_throw_error_wrapping_catched_IOException() throws IOException {
  IOException cause=new IOException();
  when(diff.diff(actual,expected,charset)).thenThrow(cause);
  try {
    files.assertHasContent(someInfo(),actual,expected,charset);
    fail("Expected a FilesException to be thrown");
  }
 catch (  FilesException e) {
    assertSame(cause,e.getCause());
  }
}
