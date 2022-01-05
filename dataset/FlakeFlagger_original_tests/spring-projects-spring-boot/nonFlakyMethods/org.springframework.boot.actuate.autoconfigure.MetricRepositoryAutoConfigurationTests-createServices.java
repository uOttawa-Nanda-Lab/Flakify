@Test public void createServices() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SyncTaskExecutorConfiguration.class,MetricRepositoryAutoConfiguration.class);
  DefaultGaugeService gaugeService=context.getBean(DefaultGaugeService.class);
  assertNotNull(gaugeService);
  assertNotNull(context.getBean(DefaultCounterService.class));
  gaugeService.submit("foo",2.7);
  assertEquals(2.7,context.getBean(MetricReader.class).findOne("gauge.foo").getValue());
  context.close();
}
