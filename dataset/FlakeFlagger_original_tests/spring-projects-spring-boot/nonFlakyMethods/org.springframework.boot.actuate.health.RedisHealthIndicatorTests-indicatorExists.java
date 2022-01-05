@Test public void indicatorExists(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,RedisAutoConfiguration.class,EndpointAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(RedisConnectionFactory.class).length);
  RedisHealthIndicator healthIndicator=this.context.getBean(RedisHealthIndicator.class);
  assertNotNull(healthIndicator);
}
