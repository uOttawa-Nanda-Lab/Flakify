@Test public void should_decrement() throws Exception {
  InternalCounterImpl counter=(InternalCounterImpl)decr();
  assertThat(counter.get()).isEqualTo(-1L);
  assertThat(counter.getInternalCounterDelta()).isEqualTo(-1L);
}
