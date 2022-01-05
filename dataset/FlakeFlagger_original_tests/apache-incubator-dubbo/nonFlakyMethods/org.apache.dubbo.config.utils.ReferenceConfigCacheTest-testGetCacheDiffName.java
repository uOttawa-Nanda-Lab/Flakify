@Test public void testGetCacheDiffName() throws Exception {
  ReferenceConfigCache cache=ReferenceConfigCache.getCache();
  MockReferenceConfig config=buildMockReferenceConfig("FooService","group1","1.0.0");
  String value=cache.get(config);
  assertTrue(config.isGetMethodRun());
  assertEquals("0",value);
  cache=ReferenceConfigCache.getCache("foo");
  config=buildMockReferenceConfig("FooService","group1","1.0.0");
  value=cache.get(config);
  assertTrue(config.isGetMethodRun());
  assertEquals("1",value);
}
