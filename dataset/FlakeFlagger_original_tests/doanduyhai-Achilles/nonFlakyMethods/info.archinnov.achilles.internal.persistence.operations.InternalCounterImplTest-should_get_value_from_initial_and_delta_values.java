@Test public void should_get_value_from_initial_and_delta_values() throws Exception {
  assertThat(new InternalCounterImpl(1L,10L).get()).isEqualTo(11L);
}
