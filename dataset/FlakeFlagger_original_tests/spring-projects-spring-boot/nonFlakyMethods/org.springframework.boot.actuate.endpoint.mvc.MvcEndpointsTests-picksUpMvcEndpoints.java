@Test public void picksUpMvcEndpoints() throws Exception {
  this.context.getDefaultListableBeanFactory().registerSingleton("endpoint",new EndpointMvcAdapter(new TestEndpoint()));
  this.endpoints.setApplicationContext(this.context);
  this.endpoints.afterPropertiesSet();
  assertEquals(1,this.endpoints.getEndpoints().size());
}
