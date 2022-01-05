@Test public void cannotAccessUnauthorizedRequest() throws Exception {
  initVictimWithMockAuthManager();
  final String resourcePath="/g1.css";
  final String requestUri=PATH_RESOURCES + resourcePath;
  when(mockAuthorizationManager.isAuthorized(resourcePath)).thenReturn(true);
  requestGroupByUri(requestUri,new RequestBuilder(requestUri){
    @Override protected HttpServletRequest newRequest(){
      final HttpServletRequest request=super.newRequest();
      when(request.getParameter(PARAM_RESOURCE_ID)).thenReturn(resourcePath);
      return request;
    }
  }
,mockFilterChain);
  verifyChainIsNotCalled(mockFilterChain);
}
