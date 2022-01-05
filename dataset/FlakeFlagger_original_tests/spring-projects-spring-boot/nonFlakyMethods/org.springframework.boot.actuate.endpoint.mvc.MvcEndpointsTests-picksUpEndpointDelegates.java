@Test public void picksUpEndpointDelegates() throws Exception {
  this.context.getDefaultListableBeanFactory().registerSingleton("endpoint",new TestEndpoint());
  this.endpoints.setApplicationContext(this.context);
  this.endpoints.afterPropertiesSet();
  assertEquals(1,this.endpoints.getEndpoints().size());
}
