@Test public void should_return_empty_mutable_ConcurrentHashMap(){
  ConcurrentMap<String,Integer> map=Maps.newConcurrentHashMap();
  assertThat(map).isInstanceOf(ConcurrentHashMap.class);
  assertThat(map).isEmpty();
  map.put("abc",123);
  assertThat(map).containsExactly(MapEntry.entry("abc",123));
}
