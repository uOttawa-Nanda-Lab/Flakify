@Test public void testCacheProvider() throws Exception {
  CacheFactory cacheFactory=ExtensionLoader.getExtensionLoader(CacheFactory.class).getAdaptiveExtension();
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put("findCache.cache","threadlocal");
  URL url=new URL("dubbo","127.0.0.1",29582,"org.apache.dubbo.config.cache.CacheService",parameters);
  Invocation invocation=new RpcInvocation("findCache",new Class[]{String.class},new String[]{"0"},null,null);
  Cache cache=cacheFactory.getCache(url,invocation);
  assertTrue(cache instanceof ThreadLocalCache);
}
