@Test public void codahaleInstalledIfPresent(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SyncTaskExecutorConfiguration.class,WriterConfig.class,MetricRepositoryAutoConfiguration.class);
  DefaultGaugeService gaugeService=context.getBean(DefaultGaugeService.class);
  assertNotNull(gaugeService);
  gaugeService.submit("foo",2.7);
  MetricRegistry registry=context.getBean(MetricRegistry.class);
  @SuppressWarnings("unchecked") Gauge<Double> gauge=(Gauge<Double>)registry.getMetrics().get("gauge.foo");
  assertEquals(new Double(2.7),gauge.getValue());
  context.close();
}
