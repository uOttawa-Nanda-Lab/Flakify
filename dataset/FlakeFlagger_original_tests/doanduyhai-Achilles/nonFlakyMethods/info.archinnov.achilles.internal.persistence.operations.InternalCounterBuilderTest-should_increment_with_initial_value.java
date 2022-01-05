@Test public void should_increment_with_initial_value() throws Exception {
  InternalCounterImpl counter=(InternalCounterImpl)initialValue(10L);
  counter.incr();
  assertThat(counter.get()).isEqualTo(11L);
  assertThat(counter.getInternalCounterDelta()).isEqualTo(1L);
}
