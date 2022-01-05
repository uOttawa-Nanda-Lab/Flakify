@Test public void getDefaultCache(){
  @SuppressWarnings("unchecked") AdvancedCache<Object,Object> cache=mock(AdvancedCache.class);
  when(this.manager.getCache()).thenReturn(cache);
  when(cache.getAdvancedCache()).thenReturn(cache);
  Cache<Object,Object> result=this.subject.getCache();
  assertNotSame(cache,result);
  assertEquals(result,cache);
  assertSame(this.subject,result.getCacheManager());
}
