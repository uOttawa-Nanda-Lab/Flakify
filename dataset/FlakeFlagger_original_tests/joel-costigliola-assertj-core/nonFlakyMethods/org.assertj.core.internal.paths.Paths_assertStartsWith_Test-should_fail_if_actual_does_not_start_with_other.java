@Test public void should_fail_if_actual_does_not_start_with_other() throws IOException {
  when(actual.toRealPath()).thenReturn(canonicalActual);
  when(other.toRealPath()).thenReturn(canonicalOther);
  when(canonicalActual.startsWith(canonicalOther)).thenReturn(false);
  try {
    paths.assertStartsWith(info,actual,other);
    wasExpectingAssertionError();
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldStartWith(actual,other));
  }
}
