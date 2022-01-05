@Test public void should_get_typed_or_default() throws Exception {
  TypedMap map=new TypedMap();
  map.put("key",CounterBuilder.incr(11L));
  Counter counter=map.<Counter>getTypedOr("key",CounterBuilder.incr(10L));
  assertThat(counter).isNotNull();
  assertThat(counter.get()).isEqualTo(11L);
}
