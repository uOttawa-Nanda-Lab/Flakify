@Test public void should_throw_error_wrapping_catched_IOException() throws IOException {
  IOException cause=new IOException();
  when(diff.diff(actual,expected)).thenThrow(cause);
  try {
    files.assertSameContentAs(someInfo(),actual,expected);
    fail("Expected a FilesException to be thrown");
  }
 catch (  FilesException e) {
    assertSame(cause,e.getCause());
  }
}
