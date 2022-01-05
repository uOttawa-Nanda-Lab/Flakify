@Test public void should_decr() throws Exception {
  Counter counter=CounterBuilder.decr();
  assertThat(counter.get()).isEqualTo(-1L);
}
