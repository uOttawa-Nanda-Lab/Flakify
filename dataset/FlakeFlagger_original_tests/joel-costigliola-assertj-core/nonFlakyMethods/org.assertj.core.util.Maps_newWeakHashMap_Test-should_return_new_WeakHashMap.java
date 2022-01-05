@Test public void should_return_new_WeakHashMap(){
  Map<String,Integer> map1=Maps.newWeakHashMap();
  Map<String,Integer> map2=Maps.newWeakHashMap();
  assertThat(map2).isNotSameAs(map1);
  map1.put("abc",123);
  assertThat(map2).isEmpty();
}
