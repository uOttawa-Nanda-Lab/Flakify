@Test public void should_return_empty_mutable_HashMap(){
  Map<String,Integer> map=Maps.newHashMap();
  assertThat(map).isInstanceOf(HashMap.class);
  assertThat(map).isEmpty();
  map.put("abc",123);
  assertThat(map).containsExactly(MapEntry.entry("abc",123));
}
