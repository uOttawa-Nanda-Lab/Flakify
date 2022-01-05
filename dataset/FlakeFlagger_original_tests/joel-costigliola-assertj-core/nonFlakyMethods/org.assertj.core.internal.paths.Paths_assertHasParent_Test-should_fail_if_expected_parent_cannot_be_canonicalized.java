@Test public void should_fail_if_expected_parent_cannot_be_canonicalized() throws IOException {
  final IOException exception=new IOException();
  when(actual.toRealPath()).thenReturn(canonicalActual);
  when(expected.toRealPath()).thenThrow(exception);
  try {
    paths.assertHasParent(info,actual,expected);
    fail("expected a PathsException here");
  }
 catch (  PathsException e) {
    assertThat(e).hasMessage("failed to resolve argument real path");
    assertThat(e.getCause()).isSameAs(exception);
  }
}
