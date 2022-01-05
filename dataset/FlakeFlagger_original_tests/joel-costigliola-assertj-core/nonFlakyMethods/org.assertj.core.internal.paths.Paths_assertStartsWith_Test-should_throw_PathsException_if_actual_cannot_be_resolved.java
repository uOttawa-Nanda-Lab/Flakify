@Test public void should_throw_PathsException_if_actual_cannot_be_resolved() throws IOException {
  final IOException exception=new IOException();
  when(actual.toRealPath()).thenThrow(exception);
  try {
    paths.assertStartsWith(info,actual,other);
    fail("was expecting a PathsException here");
  }
 catch (  PathsException e) {
    assertThat(e).hasMessage("failed to resolve actual real path");
    assertThat(e.getCause()).isSameAs(exception);
  }
}
