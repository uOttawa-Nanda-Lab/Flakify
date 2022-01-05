@Test public void should_decrement_n() throws Exception {
  InternalCounterImpl counter=(InternalCounterImpl)decr(5L);
  assertThat(counter.get()).isEqualTo(-5L);
  assertThat(counter.getInternalCounterDelta()).isEqualTo(-5L);
}
