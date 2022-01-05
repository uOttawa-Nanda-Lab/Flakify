@Test public void shouldChainWhenFilterIsNotEnabled() throws Exception {
  victim.setEnable(false);
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verifyChainIsCalled(mockFilterChain);
}
