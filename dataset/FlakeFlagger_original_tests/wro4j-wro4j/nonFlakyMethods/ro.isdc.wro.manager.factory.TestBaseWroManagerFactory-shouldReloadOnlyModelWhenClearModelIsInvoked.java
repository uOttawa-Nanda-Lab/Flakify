@Test public void shouldReloadOnlyModelWhenClearModelIsInvoked() throws Exception {
  victim=new BaseWroManagerFactory().setModelFactory(mockModelFactory).setCacheStrategy(mockCacheStrategy);
  final WroManager manager=victim.create();
  manager.onModelPeriodChanged(0);
  Context.get().getConfig().reloadModel();
  verify(mockModelFactory,Mockito.times(1)).destroy();
  verify(mockCacheStrategy,Mockito.never()).clear();
}
