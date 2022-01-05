@Test public void should_get_value_from_initial_value() throws Exception {
  assertThat(new InternalCounterImpl(null,10L).get()).isEqualTo(10L);
}
