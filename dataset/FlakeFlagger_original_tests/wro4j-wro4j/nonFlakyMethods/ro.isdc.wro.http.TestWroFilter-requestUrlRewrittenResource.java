@Test public void requestUrlRewrittenResource() throws Exception {
  initVictimWithMockAuthManager();
  final String resourcePath="classpath:ro/isdc/wro/http/2.css";
  when(mockAuthorizationManager.isAuthorized(resourcePath)).thenReturn(true);
  final String requestUri=PATH_RESOURCES + "?id=" + resourcePath;
  requestGroupByUri(requestUri,new RequestBuilder(requestUri){
    @Override protected HttpServletRequest newRequest(){
      final HttpServletRequest request=super.newRequest();
      when(request.getParameter(PARAM_RESOURCE_ID)).thenReturn(resourcePath);
      return request;
    }
  }
);
}
