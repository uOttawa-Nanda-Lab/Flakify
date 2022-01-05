@Test public void indicatorExists(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,SolrAutoConfiguration.class,EndpointAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(SolrServer.class).length);
  SolrHealthIndicator healthIndicator=this.context.getBean(SolrHealthIndicator.class);
  assertNotNull(healthIndicator);
}
