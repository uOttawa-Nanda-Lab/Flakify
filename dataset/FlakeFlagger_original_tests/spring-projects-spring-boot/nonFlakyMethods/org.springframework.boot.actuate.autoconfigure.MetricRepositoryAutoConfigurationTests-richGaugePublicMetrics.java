@Test public void richGaugePublicMetrics(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(RichGaugeReaderConfig.class,MetricRepositoryAutoConfiguration.class);
  RichGaugeReader richGaugeReader=context.getBean(RichGaugeReader.class);
  assertNotNull(richGaugeReader);
  given(richGaugeReader.findAll()).willReturn(Collections.singletonList(new RichGauge("bar",3.7d)));
  RichGaugeReaderPublicMetrics publicMetrics=context.getBean(RichGaugeReaderPublicMetrics.class);
  assertNotNull(publicMetrics);
  Collection<Metric<?>> metrics=publicMetrics.metrics();
  assertNotNull(metrics);
  assertEquals(metrics.size(),6);
  assertHasMetric(metrics,new Metric<Double>("bar.val",3.7d));
  assertHasMetric(metrics,new Metric<Double>("bar.avg",3.7d));
  assertHasMetric(metrics,new Metric<Double>("bar.min",3.7d));
  assertHasMetric(metrics,new Metric<Double>("bar.max",3.7d));
  assertHasMetric(metrics,new Metric<Double>("bar.alpha",-1.d));
  assertHasMetric(metrics,new Metric<Long>("bar.count",1L));
  context.close();
}
