@Test public void shouldNotChainWhenFilterIsEnabled() throws Exception {
  prepareValidRequest(new WroConfiguration());
  victim.setEnable(true);
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verifyChainIsNotCalled(mockFilterChain);
}
