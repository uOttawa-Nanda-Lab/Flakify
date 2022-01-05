@Test public void testCacheFactory(){
  URL url=URL.valueOf("test://test:11/test?cache=jacache&.cache.write.expire=1");
  CacheFactory cacheFactory=new MyCacheFactory();
  Invocation invocation=new NullInvocation();
  Cache cache=cacheFactory.getCache(url,invocation);
  cache.put("testKey","testValue");
  org.apache.dubbo.cache.CacheFactory factory=cacheFactory;
  org.apache.dubbo.common.URL u=org.apache.dubbo.common.URL.valueOf("test://test:11/test?cache=jacache&.cache.write.expire=1");
  org.apache.dubbo.rpc.Invocation inv=new RpcInvocation();
  org.apache.dubbo.cache.Cache c=factory.getCache(u,inv);
  String v=(String)c.get("testKey");
  Assert.assertEquals("testValue",v);
}
