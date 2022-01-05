@Test public void should_build_counter_with_initial_value() throws Exception {
  assertThat(initialValue(10L).get()).isEqualTo(10L);
}
