@Test public void should_return_new_ConcurrentHashMap(){
  ConcurrentMap<String,Integer> map1=Maps.newConcurrentHashMap();
  ConcurrentMap<String,Integer> map2=Maps.newConcurrentHashMap();
  assertThat(map2).isNotSameAs(map1);
  map1.put("abc",123);
  assertThat(map2).isEmpty();
}
