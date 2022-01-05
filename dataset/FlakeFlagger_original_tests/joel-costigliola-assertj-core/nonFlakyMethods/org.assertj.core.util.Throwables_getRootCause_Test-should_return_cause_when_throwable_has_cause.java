@Test public void should_return_cause_when_throwable_has_cause() throws Exception {
  IllegalArgumentException expectedCause=new IllegalArgumentException();
  assertThat(Throwables.getRootCause(new Throwable(expectedCause))).isSameAs(expectedCause);
}
