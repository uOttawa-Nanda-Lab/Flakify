/** 
 * Tests that in DEPLOYMENT mode the API is not exposed.
 */
@Test public void testApiCallInDEPLOYMENTMode() throws Exception {
  initChainOnErrorFilter();
  when(mockRequest.getRequestURI()).thenReturn(ReloadCacheRequestHandler.PATH_API + "/someMethod");
  setConfigurationMode(FilterConfigWroConfigurationFactory.PARAM_VALUE_DEPLOYMENT);
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verifyChainIsCalled(mockFilterChain);
}
