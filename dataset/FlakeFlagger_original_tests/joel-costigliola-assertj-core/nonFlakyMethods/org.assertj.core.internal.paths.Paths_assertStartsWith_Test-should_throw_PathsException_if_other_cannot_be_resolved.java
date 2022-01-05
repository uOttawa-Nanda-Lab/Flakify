@Test public void should_throw_PathsException_if_other_cannot_be_resolved() throws IOException {
  final IOException exception=new IOException();
  when(actual.toRealPath()).thenReturn(canonicalActual);
  when(other.toRealPath()).thenThrow(exception);
  try {
    paths.assertStartsWith(info,actual,other);
    fail("was expecting a PathsException here");
  }
 catch (  PathsException e) {
    assertThat(e).hasMessage("failed to resolve argument real path");
    assertThat(e.getCause()).isSameAs(exception);
  }
}
