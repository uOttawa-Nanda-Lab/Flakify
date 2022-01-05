@Test public void testGetCacheSameReference() throws Exception {
  ReferenceConfigCache cache=ReferenceConfigCache.getCache();
  MockReferenceConfig config=buildMockReferenceConfig("FooService","group1","1.0.0");
  String value=cache.get(config);
  assertTrue(config.isGetMethodRun());
  assertEquals("0",value);
  MockReferenceConfig configCopy=buildMockReferenceConfig("FooService","group1","1.0.0");
  value=cache.get(configCopy);
  assertFalse(configCopy.isGetMethodRun());
  assertEquals("0",value);
}
