@Test public void shouldReloadOnlyCacheWhenClearCacheIsInvoked() throws Exception {
  victim=new BaseWroManagerFactory().setModelFactory(mockModelFactory).setCacheStrategy(mockCacheStrategy);
  final WroManager manager=victim.create();
  manager.onCachePeriodChanged(0);
  Context.get().getConfig().reloadCache();
  verify(mockModelFactory,Mockito.never()).destroy();
  verify(mockCacheStrategy,Mockito.times(1)).clear();
}
