@Test public void testDoFilterInDEPLOYMENTMode() throws Exception {
  when(mockRequest.getRequestURI()).thenReturn("/g2.js");
  victim.setWroManagerFactory(createValidManagerFactory());
  setConfigurationMode(FilterConfigWroConfigurationFactory.PARAM_VALUE_DEPLOYMENT);
  victim.init(mockFilterConfig);
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
}
