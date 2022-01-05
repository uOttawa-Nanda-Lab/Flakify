@Test public void should_get_typed_when_present() throws Exception {
  TypedMap map=new TypedMap();
  map.put("key",CounterBuilder.incr(10L));
  Counter counter=map.<Counter>getTyped("key");
  assertThat(counter).isNotNull();
  assertThat(counter.get()).isEqualTo(10L);
}
