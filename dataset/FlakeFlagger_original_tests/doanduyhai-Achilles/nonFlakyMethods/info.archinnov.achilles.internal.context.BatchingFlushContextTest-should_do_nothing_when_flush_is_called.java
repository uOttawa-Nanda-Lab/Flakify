@Test public void should_do_nothing_when_flush_is_called() throws Exception {
  context.statementWrappers.add(bsWrapper);
  context.flush();
  assertThat(context.statementWrappers).containsExactly(bsWrapper);
}
