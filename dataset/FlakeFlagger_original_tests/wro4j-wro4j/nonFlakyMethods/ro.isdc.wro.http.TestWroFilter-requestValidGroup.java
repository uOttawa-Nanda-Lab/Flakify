@Test public void requestValidGroup() throws Exception {
  initChainOnErrorFilter();
  requestGroupByUri("/folder/g1.css");
}
