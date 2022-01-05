@Test public void should_throw_PathsException_on_io_error() throws IOException {
  final IOException exception=new IOException();
  when(actual.toRealPath()).thenThrow(exception);
  try {
    paths.assertIsCanonical(info,actual);
    fail("Expected a PathsException here");
  }
 catch (  PathsException e) {
    assertThat(e).hasMessage("failed to resolve actual real path");
    assertThat(e.getCause()).isSameAs(exception);
  }
}
