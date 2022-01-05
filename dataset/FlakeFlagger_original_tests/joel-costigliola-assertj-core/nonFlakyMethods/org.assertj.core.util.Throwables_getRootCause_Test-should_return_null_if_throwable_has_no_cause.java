@Test public void should_return_null_if_throwable_has_no_cause() throws Exception {
  assertThat(Throwables.getRootCause(new Throwable())).isNull();
}
