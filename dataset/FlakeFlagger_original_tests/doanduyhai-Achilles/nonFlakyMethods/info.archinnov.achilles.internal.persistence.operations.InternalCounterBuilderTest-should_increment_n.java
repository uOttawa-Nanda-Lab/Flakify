@Test public void should_increment_n() throws Exception {
  InternalCounterImpl counter=(InternalCounterImpl)incr(5L);
  assertThat(counter.get()).isEqualTo(5L);
  assertThat(counter.getInternalCounterDelta()).isEqualTo(5L);
}
