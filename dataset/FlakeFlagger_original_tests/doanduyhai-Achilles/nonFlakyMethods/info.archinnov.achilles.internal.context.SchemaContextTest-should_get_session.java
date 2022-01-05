@Test public void should_get_session() throws Exception {
  assertThat(context.getSession()).isSameAs(session);
}
