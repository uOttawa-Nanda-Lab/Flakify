@Test public void diskSpaceHealthIndicator(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(HealthIndicatorAutoConfiguration.class);
  this.context.refresh();
  Map<String,HealthIndicator> beans=this.context.getBeansOfType(HealthIndicator.class);
  assertEquals(1,beans.size());
  assertEquals(DiskSpaceHealthIndicator.class,beans.values().iterator().next().getClass());
}
