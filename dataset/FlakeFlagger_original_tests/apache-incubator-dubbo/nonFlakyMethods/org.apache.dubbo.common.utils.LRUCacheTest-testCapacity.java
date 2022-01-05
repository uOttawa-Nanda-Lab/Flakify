@Test public void testCapacity() throws Exception {
  LRUCache<String,Integer> cache=new LRUCache<String,Integer>();
  assertThat(cache.getMaxCapacity(),equalTo(1000));
  cache.setMaxCapacity(10);
  assertThat(cache.getMaxCapacity(),equalTo(10));
}
