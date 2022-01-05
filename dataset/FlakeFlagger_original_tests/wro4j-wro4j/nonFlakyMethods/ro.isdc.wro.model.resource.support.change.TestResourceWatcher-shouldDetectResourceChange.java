@Test public void shouldDetectResourceChange() throws Exception {
  final AtomicBoolean flag=new AtomicBoolean(false);
  victim=new ResourceWatcher(){
    @Override void onGroupChanged(    final CacheKey cacheEntry){
      super.onGroupChanged(cacheEntry);
      Assert.assertEquals(GROUP_NAME,cacheEntry.getGroupName());
      flag.set(true);
    }
  }
;
  createDefaultInjector().inject(victim);
  victim.check(cacheEntry);
  assertFalse(victim.getResourceChangeDetector().checkChangeForGroup(RESOURCE_URI,GROUP_NAME));
  Mockito.when(mockLocator.locate(Mockito.anyString())).thenReturn(new ByteArrayInputStream("different".getBytes()));
  victim.check(cacheEntry);
  assertTrue(victim.getResourceChangeDetector().checkChangeForGroup(RESOURCE_URI,GROUP_NAME));
  assertTrue(flag.get());
}
