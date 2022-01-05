@Test public void indicatorExists(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,EndpointAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(MongoTemplate.class).length);
  MongoHealthIndicator healthIndicator=this.context.getBean(MongoHealthIndicator.class);
  assertNotNull(healthIndicator);
}
