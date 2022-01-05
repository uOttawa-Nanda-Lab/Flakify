@Test public void shouldAssumeResourceNotChangedWhenStreamIsUnavailable() throws Exception {
  victim=new ResourceWatcher(){
    @Override void onGroupChanged(    final CacheKey cacheEntry){
      super.onGroupChanged(cacheEntry);
      Assert.fail("Should not detect the change");
    }
  }
;
  createDefaultInjector().inject(victim);
  final ResourceChangeDetector mockChangeDetector=Mockito.spy(victim.getResourceChangeDetector());
  Mockito.when(mockLocator.locate(Mockito.anyString())).thenThrow(new IOException("Resource is unavailable"));
  victim.check(cacheEntry);
  verify(mockChangeDetector,never()).checkChangeForGroup(Mockito.anyString(),Mockito.anyString());
}
