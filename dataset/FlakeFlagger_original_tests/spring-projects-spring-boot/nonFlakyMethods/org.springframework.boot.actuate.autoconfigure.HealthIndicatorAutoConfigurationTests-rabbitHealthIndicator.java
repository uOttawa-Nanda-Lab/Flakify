@Test public void rabbitHealthIndicator(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(RabbitAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"health.diskspace.enabled:false");
  this.context.refresh();
  Map<String,HealthIndicator> beans=this.context.getBeansOfType(HealthIndicator.class);
  assertEquals(1,beans.size());
  assertEquals(RabbitHealthIndicator.class,beans.values().iterator().next().getClass());
}
