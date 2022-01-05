/** 
 * Fix the issue described <a href="https://github.com/alexo/wro4j/issues/72">here</a>.
 */
@Test public void shouldNotDetectErroneouslyChange() throws Exception {
  final AtomicBoolean groupChanged=new AtomicBoolean(false);
  final AtomicBoolean resourceChanged=new AtomicBoolean(false);
  victim=new ResourceWatcher(){
    @Override void onResourceChanged(    final Resource resource){
      resourceChanged.set(true);
    }
    @Override void onGroupChanged(    final CacheKey key){
      groupChanged.set(true);
    }
  }
;
  createDefaultInjector().inject(victim);
  victim.check(cacheEntry2);
  when(mockLocator.locate(RESOURCE_FIRST)).thenAnswer(answerWithContent("changed"));
  victim.check(cacheEntry2);
  assertTrue(groupChanged.get());
  assertTrue(resourceChanged.get());
  groupChanged.set(false);
  resourceChanged.set(false);
  victim.check(cacheEntry2);
  assertFalse(groupChanged.get());
  assertFalse(resourceChanged.get());
}
