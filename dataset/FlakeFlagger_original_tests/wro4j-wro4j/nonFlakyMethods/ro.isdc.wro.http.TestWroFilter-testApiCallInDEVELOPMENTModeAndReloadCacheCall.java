/** 
 * Tests that in DEVELOPMENT mode the API is exposed.
 */
@Test public void testApiCallInDEVELOPMENTModeAndReloadCacheCall() throws Exception {
  when(mockRequest.getRequestURI()).thenReturn(ReloadCacheRequestHandler.ENDPOINT_URI);
  when(mockResponse.getWriter()).thenReturn(new PrintWriter(System.out));
  final CacheStrategy<CacheKey,CacheValue> mockCacheStrategy=mock(CacheStrategy.class);
  final WroManagerFactory managerFactory=new BaseWroManagerFactory().setCacheStrategy(mockCacheStrategy);
  victim.setWroManagerFactory(managerFactory);
  victim.init(mockFilterConfig);
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verifyChainIsNotCalled(mockFilterChain);
  verify(mockCacheStrategy).clear();
}
