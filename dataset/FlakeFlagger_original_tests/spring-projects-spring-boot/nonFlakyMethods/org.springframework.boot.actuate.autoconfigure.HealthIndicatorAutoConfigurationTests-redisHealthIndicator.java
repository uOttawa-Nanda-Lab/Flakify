@Test public void redisHealthIndicator(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(RedisAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"health.diskspace.enabled:false");
  this.context.refresh();
  Map<String,HealthIndicator> beans=this.context.getBeansOfType(HealthIndicator.class);
  assertEquals(1,beans.size());
  assertEquals(RedisHealthIndicator.class,beans.values().iterator().next().getClass());
}
