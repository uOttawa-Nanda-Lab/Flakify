@Test public void should_build_counter_with_null_initial_value() throws Exception {
  assertThat(initialValue(null).get()).isNull();
}
