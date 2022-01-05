@Test public void should_return_default() throws Exception {
  TypedMap map=new TypedMap();
  Counter counter=map.<Counter>getTypedOr("key",CounterBuilder.incr(10L));
  assertThat(counter).isNotNull();
  assertThat(counter.get()).isEqualTo(10L);
}
