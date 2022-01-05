@Test public void should_fail_if_actual_cannot_be_canonicalized() throws IOException {
  final IOException exception=new IOException();
  when(actual.toRealPath()).thenThrow(exception);
  try {
    paths.assertHasParent(info,actual,expected);
    fail("expected a PathsException here");
  }
 catch (  PathsException e) {
    assertThat(e).hasMessage("failed to resolve actual real path");
    assertThat(e.getCause()).isSameAs(exception);
  }
}
