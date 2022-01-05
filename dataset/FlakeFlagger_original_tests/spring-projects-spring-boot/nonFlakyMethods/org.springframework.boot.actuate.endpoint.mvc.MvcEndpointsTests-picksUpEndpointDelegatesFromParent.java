@Test public void picksUpEndpointDelegatesFromParent() throws Exception {
  StaticApplicationContext parent=new StaticApplicationContext();
  this.context.setParent(parent);
  parent.getDefaultListableBeanFactory().registerSingleton("endpoint",new TestEndpoint());
  this.endpoints.setApplicationContext(this.context);
  this.endpoints.afterPropertiesSet();
  assertEquals(1,this.endpoints.getEndpoints().size());
}
