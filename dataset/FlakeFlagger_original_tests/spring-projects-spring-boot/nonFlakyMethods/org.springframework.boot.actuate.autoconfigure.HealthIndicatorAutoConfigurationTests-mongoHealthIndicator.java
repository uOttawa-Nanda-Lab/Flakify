@Test public void mongoHealthIndicator(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"health.diskspace.enabled:false");
  this.context.refresh();
  Map<String,HealthIndicator> beans=this.context.getBeansOfType(HealthIndicator.class);
  assertEquals(1,beans.size());
  assertEquals(MongoHealthIndicator.class,beans.values().iterator().next().getClass());
}
