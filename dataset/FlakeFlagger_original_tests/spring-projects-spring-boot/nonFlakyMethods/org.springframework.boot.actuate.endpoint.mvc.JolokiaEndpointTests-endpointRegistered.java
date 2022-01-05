@Test public void endpointRegistered() throws Exception {
  Set<? extends MvcEndpoint> values=this.endpoints.getEndpoints();
  assertEquals(1,values.size());
  assertTrue(values.iterator().next() instanceof JolokiaMvcEndpoint);
}
