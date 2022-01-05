/** 
 * Tests that in DEPLOYMENT mode the API is not exposed.
 */
@Test public void testApiCallInDEVELOPMENTModeAndInvalidApiCall() throws Exception {
  initChainOnErrorFilter();
  when(mockRequest.getRequestURI()).thenReturn(ReloadCacheRequestHandler.PATH_API + "/someMethod");
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verifyChainIsCalled(mockFilterChain);
}
