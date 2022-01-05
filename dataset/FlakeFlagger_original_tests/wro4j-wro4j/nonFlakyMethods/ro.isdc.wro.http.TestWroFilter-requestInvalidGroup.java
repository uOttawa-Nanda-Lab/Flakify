@Test public void requestInvalidGroup() throws Exception {
  initChainOnErrorFilter();
  requestGroupByUri("/folder/INVALID_GROUP.css",mockFilterChain);
  verifyChainIsCalled(mockFilterChain);
}
