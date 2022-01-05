@Test public void should_list_intercepted_events() throws Exception {
  assertThat(interceptor.events()).containsExactly(PRE_PERSIST,PRE_UPDATE);
}
