@Test public void provideAdditionalWriter(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SyncTaskExecutorConfiguration.class,WriterConfig.class,MetricRepositoryAutoConfiguration.class);
  DefaultGaugeService gaugeService=context.getBean(DefaultGaugeService.class);
  assertNotNull(gaugeService);
  gaugeService.submit("foo",2.7);
  MetricWriter writer=context.getBean("writer",MetricWriter.class);
  verify(writer).set(any(Metric.class));
  context.close();
}
