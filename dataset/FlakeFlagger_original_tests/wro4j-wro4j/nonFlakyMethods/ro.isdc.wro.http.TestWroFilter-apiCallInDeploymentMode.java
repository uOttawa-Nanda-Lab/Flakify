/** 
 * Tests that in DEPLOYMENT mode the API is NOT exposed.
 */
@Test public void apiCallInDeploymentMode() throws Exception {
  final Properties props=new Properties();
  props.setProperty(ConfigConstants.debug.name(),Boolean.FALSE.toString());
  final WroFilter theFilter=new WroFilter(){
    @Override protected ObjectFactory<WroConfiguration> newWroConfigurationFactory(    final FilterConfig filterConfig){
      final PropertyWroConfigurationFactory factory=new PropertyWroConfigurationFactory(props);
      return factory;
    }
  }
;
  final HttpServletRequest request=mock(HttpServletRequest.class,Mockito.RETURNS_DEEP_STUBS);
  when(request.getRequestURI()).thenReturn(ReloadCacheRequestHandler.ENDPOINT_URI);
  final HttpServletResponse response=mock(HttpServletResponse.class);
  when(response.getWriter()).thenReturn(new PrintWriter(System.out));
  theFilter.init(mockFilterConfig);
  theFilter.doFilter(request,response,mockFilterChain);
  verifyChainIsCalled(mockFilterChain);
}
