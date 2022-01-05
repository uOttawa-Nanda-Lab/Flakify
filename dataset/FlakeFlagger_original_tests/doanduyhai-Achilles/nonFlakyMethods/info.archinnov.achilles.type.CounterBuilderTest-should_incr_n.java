@Test public void should_incr_n() throws Exception {
  Counter counter=CounterBuilder.incr(10L);
  assertThat(counter.get()).isEqualTo(10L);
}
