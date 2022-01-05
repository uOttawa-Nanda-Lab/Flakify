@Test public void should_decr_n() throws Exception {
  Counter counter=CounterBuilder.decr(10L);
  assertThat(counter.get()).isEqualTo(-10L);
}
