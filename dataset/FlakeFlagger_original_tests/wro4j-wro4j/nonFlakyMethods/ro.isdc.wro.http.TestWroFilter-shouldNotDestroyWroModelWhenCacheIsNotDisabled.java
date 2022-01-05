@Test public void shouldNotDestroyWroModelWhenCacheIsNotDisabled() throws Exception {
  final WroConfiguration config=new WroConfiguration();
  config.setDisableCache(false);
  prepareValidRequest(config);
  final WroModelFactory mockModelFactory=Mockito.spy(createValidModelFactory());
  victim.setWroManagerFactory(new BaseWroManagerFactory().setModelFactory(mockModelFactory));
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verify(mockModelFactory,Mockito.never()).destroy();
}
