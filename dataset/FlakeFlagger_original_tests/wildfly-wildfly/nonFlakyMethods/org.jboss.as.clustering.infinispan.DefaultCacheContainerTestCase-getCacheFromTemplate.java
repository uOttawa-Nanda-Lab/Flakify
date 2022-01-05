@Deprecated @Test public void getCacheFromTemplate(){
  @SuppressWarnings("unchecked") AdvancedCache<Object,Object> cache=mock(AdvancedCache.class);
  String templateName="template";
  when(this.manager.getCache("other",templateName)).thenReturn(cache);
  when(cache.getAdvancedCache()).thenReturn(cache);
  Cache<Object,Object> result=this.subject.getCache("other",templateName);
  assertNotSame(cache,result);
  assertEquals(result,cache);
  assertSame(this.subject,result.getCacheManager());
}
