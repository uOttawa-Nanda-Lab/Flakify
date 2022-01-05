@Test public void getCache(){
  @SuppressWarnings("unchecked") AdvancedCache<Object,Object> cache=mock(AdvancedCache.class);
  when(this.manager.getCache("other")).thenReturn(cache);
  when(cache.getAdvancedCache()).thenReturn(cache);
  Cache<Object,Object> result=this.subject.getCache("other");
  assertNotSame(cache,result);
  assertEquals(result,cache);
  assertSame(this.subject,result.getCacheManager());
}
