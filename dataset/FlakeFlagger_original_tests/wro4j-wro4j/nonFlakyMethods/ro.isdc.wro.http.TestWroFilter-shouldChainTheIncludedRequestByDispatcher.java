@Test public void shouldChainTheIncludedRequestByDispatcher() throws Exception {
  when(mockRequest.getAttribute(DispatcherStreamLocator.ATTRIBUTE_INCLUDED_BY_DISPATCHER)).thenReturn(Boolean.TRUE);
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verify(mockManagerFactory,Mockito.never()).create();
  verifyChainIsCalled(mockFilterChain);
}
