@Test public void testDestroy() throws Exception {
  ReferenceConfigCache cache=ReferenceConfigCache.getCache();
  MockReferenceConfig config=buildMockReferenceConfig("FooService","group1","1.0.0");
  cache.get(config);
  MockReferenceConfig configCopy=buildMockReferenceConfig("XxxService","group1","1.0.0");
  cache.get(configCopy);
  assertEquals(2,cache.cache.size());
  cache.destroy(config);
  assertTrue(config.isDestroyMethodRun());
  assertEquals(1,cache.cache.size());
  cache.destroy(configCopy);
  assertTrue(configCopy.isDestroyMethodRun());
  assertEquals(0,cache.cache.size());
}
