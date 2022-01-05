@Test public void shouldDetectChangeOfImportedResource() throws Exception {
  final String importResourceUri="imported.css";
  final AtomicBoolean groupChanged=new AtomicBoolean(false);
  final AtomicBoolean importResourceChanged=new AtomicBoolean(false);
  final CacheKey cacheEntry=new CacheKey(GROUP_NAME,ResourceType.CSS,true);
  victim=new ResourceWatcher(){
    @Override void onResourceChanged(    final Resource resource){
      importResourceChanged.set(true);
    }
    @Override void onGroupChanged(    final CacheKey key){
      groupChanged.set(true);
    }
  }
;
  createDefaultInjector().inject(victim);
  when(mockLocator.locate(Mockito.anyString())).thenAnswer(answerWithContent("initial"));
  when(mockLocator.locate("/" + Mockito.eq(RESOURCE_URI))).thenAnswer(answerWithContent(String.format("@import url(%s)",importResourceUri)));
  victim.check(cacheEntry);
  when(mockLocator.locate(Mockito.anyString())).thenAnswer(answerWithContent("changed"));
  when(mockLocator.locate("/" + Mockito.eq(RESOURCE_URI))).thenAnswer(answerWithContent(String.format("@import url(%s)",importResourceUri)));
  victim.check(cacheEntry);
  assertTrue(groupChanged.get());
  assertTrue(importResourceChanged.get());
}
