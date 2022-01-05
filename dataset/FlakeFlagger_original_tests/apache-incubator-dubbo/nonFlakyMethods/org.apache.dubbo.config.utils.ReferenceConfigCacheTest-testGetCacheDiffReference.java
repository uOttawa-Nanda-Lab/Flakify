@Test public void testGetCacheDiffReference() throws Exception {
  ReferenceConfigCache cache=ReferenceConfigCache.getCache();
  MockReferenceConfig config=buildMockReferenceConfig("FooService","group1","1.0.0");
  String value=cache.get(config);
  assertTrue(config.isGetMethodRun());
  assertEquals("0",value);
  MockReferenceConfig configCopy=buildMockReferenceConfig("XxxService","group1","1.0.0");
  value=cache.get(configCopy);
  assertTrue(configCopy.isGetMethodRun());
  assertEquals("1",value);
}
