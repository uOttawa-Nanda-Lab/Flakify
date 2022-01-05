@Test public void should_increment_n_with_initial_value() throws Exception {
  InternalCounterImpl counter=(InternalCounterImpl)initialValue(10L);
  counter.incr(2L);
  assertThat(counter.get()).isEqualTo(12L);
  assertThat(counter.getInternalCounterDelta()).isEqualTo(2L);
}
