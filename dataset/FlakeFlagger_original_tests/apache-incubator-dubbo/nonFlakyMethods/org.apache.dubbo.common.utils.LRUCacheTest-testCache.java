@Test public void testCache() throws Exception {
  LRUCache<String,Integer> cache=new LRUCache<String,Integer>(3);
  cache.put("one",1);
  cache.put("two",2);
  cache.put("three",3);
  assertThat(cache.get("one"),equalTo(1));
  assertThat(cache.get("two"),equalTo(2));
  assertThat(cache.get("three"),equalTo(3));
  assertThat(cache.size(),equalTo(3));
  cache.put("four",4);
  assertThat(cache.size(),equalTo(3));
  assertFalse(cache.containsKey("one"));
  assertTrue(cache.containsKey("two"));
  assertTrue(cache.containsKey("three"));
  assertTrue(cache.containsKey("four"));
  cache.remove("four");
  assertThat(cache.size(),equalTo(2));
  cache.put("five",5);
  assertFalse(cache.containsKey("four"));
  assertTrue(cache.containsKey("five"));
  assertTrue(cache.containsKey("two"));
  assertTrue(cache.containsKey("three"));
  assertThat(cache.size(),equalTo(3));
}
