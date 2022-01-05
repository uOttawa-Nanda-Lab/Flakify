@Test public void cannotProcessInvalidUri() throws Exception {
  initChainOnErrorFilter();
  requestGroupByUri("",mockFilterChain);
  verifyChainIsCalled(mockFilterChain);
}
