@Test public void combinedHealthIndicator(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(MongoAutoConfiguration.class,RedisAutoConfiguration.class,MongoDataAutoConfiguration.class,SolrAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  this.context.refresh();
  Map<String,HealthIndicator> beans=this.context.getBeansOfType(HealthIndicator.class);
  assertEquals(4,beans.size());
}
