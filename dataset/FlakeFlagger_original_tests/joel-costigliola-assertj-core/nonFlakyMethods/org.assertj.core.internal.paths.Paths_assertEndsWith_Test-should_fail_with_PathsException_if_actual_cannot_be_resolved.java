@Test public void should_fail_with_PathsException_if_actual_cannot_be_resolved() throws IOException {
  final IOException causeException=new IOException();
  when(actual.toRealPath()).thenThrow(causeException);
  try {
    paths.assertEndsWith(info,actual,other);
    fail("expected a PathsException here");
  }
 catch (  PathsException e) {
    assertThat(e).hasMessage("failed to resolve actual real path");
    assertThat(e.getCause()).isSameAs(causeException);
  }
}
