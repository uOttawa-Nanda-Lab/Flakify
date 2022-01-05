@Test public void should_decrement_with_initial_value() throws Exception {
  InternalCounterImpl counter=(InternalCounterImpl)initialValue(10L);
  counter.decr();
  assertThat(counter.get()).isEqualTo(9L);
  assertThat(counter.getInternalCounterDelta()).isEqualTo(-1L);
}
