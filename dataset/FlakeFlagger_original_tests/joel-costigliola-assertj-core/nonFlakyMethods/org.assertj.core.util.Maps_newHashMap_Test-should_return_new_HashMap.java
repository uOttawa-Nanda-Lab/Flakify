@Test public void should_return_new_HashMap(){
  Map<String,Integer> map1=Maps.newHashMap();
  Map<String,Integer> map2=Maps.newHashMap();
  assertThat(map2).isNotSameAs(map1);
  map1.put("abc",123);
  assertThat(map2).isEmpty();
}
