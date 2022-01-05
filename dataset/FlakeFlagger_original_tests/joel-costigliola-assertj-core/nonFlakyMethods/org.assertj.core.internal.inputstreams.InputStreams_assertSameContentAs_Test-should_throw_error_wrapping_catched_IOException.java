@Test public void should_throw_error_wrapping_catched_IOException() throws IOException {
  IOException cause=new IOException();
  when(diff.diff(actual,expected)).thenThrow(cause);
  try {
    inputStreams.assertSameContentAs(someInfo(),actual,expected);
    fail("Expected a InputStreamsException to be thrown");
  }
 catch (  InputStreamsException e) {
    assertSame(cause,e.getCause());
  }
}
