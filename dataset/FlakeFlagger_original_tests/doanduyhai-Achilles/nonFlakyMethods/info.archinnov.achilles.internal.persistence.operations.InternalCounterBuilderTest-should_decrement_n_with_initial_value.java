@Test public void should_decrement_n_with_initial_value() throws Exception {
  InternalCounterImpl counter=(InternalCounterImpl)initialValue(10L);
  counter.decr(2L);
  assertThat(counter.get()).isEqualTo(8L);
  assertThat(counter.getInternalCounterDelta()).isEqualTo(-2L);
}
