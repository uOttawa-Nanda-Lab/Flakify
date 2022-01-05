@Test public void shouldDestroyWroModelWhenCacheIsDisabled() throws Exception {
  final WroConfiguration config=new WroConfiguration();
  config.setDisableCache(true);
  prepareValidRequest(config);
  final WroModelFactory mockModelFactory=Mockito.spy(createValidModelFactory());
  victim.setWroManagerFactory(new BaseWroManagerFactory().setModelFactory(mockModelFactory));
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verify(mockModelFactory).destroy();
}
