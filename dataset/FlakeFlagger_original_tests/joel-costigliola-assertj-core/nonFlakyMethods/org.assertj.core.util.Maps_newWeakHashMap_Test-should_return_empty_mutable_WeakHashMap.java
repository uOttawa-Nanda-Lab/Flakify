@Test public void should_return_empty_mutable_WeakHashMap(){
  Map<String,Integer> map=Maps.newWeakHashMap();
  assertThat(map).isInstanceOf(WeakHashMap.class);
  assertThat(map).isEmpty();
  map.put("abc",123);
  assertThat(map).containsExactly(MapEntry.entry("abc",123));
}
