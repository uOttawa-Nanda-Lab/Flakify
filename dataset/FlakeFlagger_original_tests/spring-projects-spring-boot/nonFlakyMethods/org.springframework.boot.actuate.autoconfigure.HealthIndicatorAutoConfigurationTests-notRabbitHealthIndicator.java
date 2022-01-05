@Test public void notRabbitHealthIndicator(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(RabbitAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"health.rabbit.enabled:false","health.diskspace.enabled:false");
  this.context.refresh();
  Map<String,HealthIndicator> beans=this.context.getBeansOfType(HealthIndicator.class);
  assertEquals(1,beans.size());
  assertEquals(ApplicationHealthIndicator.class,beans.values().iterator().next().getClass());
}
