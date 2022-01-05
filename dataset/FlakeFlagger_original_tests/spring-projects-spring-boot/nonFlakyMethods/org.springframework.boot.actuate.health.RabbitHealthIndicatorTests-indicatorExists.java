@Test public void indicatorExists(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,RabbitAutoConfiguration.class,EndpointAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(RabbitAdmin.class).length);
  RabbitHealthIndicator healthIndicator=this.context.getBean(RabbitHealthIndicator.class);
  assertNotNull(healthIndicator);
}
