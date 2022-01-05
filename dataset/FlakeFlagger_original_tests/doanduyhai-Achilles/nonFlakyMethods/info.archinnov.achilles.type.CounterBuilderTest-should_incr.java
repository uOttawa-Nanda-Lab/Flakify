@Test public void should_incr() throws Exception {
  Counter counter=CounterBuilder.incr();
  assertThat(counter.get()).isEqualTo(1L);
}
