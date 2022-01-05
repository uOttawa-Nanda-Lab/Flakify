@Test public void testJCacheGetExpired() throws Exception {
  URL url=URL.valueOf("test://test:11/test?cache=jacache&.cache.write.expire=1");
  AbstractCacheFactory cacheFactory=getCacheFactory();
  Invocation invocation=new RpcInvocation();
  Cache cache=cacheFactory.getCache(url,invocation);
  cache.put("testKey","testValue");
  Thread.sleep(10);
  assertNull(cache.get("testKey"));
}
