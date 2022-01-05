@Test public void should_throw_PathsException_if_actual_cannot_be_canonicalized() throws IOException {
  final IOException exception=new IOException();
  when(actual.toRealPath()).thenThrow(exception);
  try {
    paths.assertHasNoParent(info,actual);
    fail("was expecting a PathsException");
  }
 catch (  PathsException e) {
    assertThat(e).hasMessage("failed to resolve actual real path");
    assertThat(e.getCause()).isSameAs(exception);
  }
}
