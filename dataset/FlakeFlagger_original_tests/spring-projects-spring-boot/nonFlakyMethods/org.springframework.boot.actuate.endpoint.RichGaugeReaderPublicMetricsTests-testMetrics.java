@Test public void testMetrics() throws Exception {
  InMemoryRichGaugeRepository repository=new InMemoryRichGaugeRepository();
  repository.set(new Metric<Double>("a",0.d,new Date()));
  repository.set(new Metric<Double>("a",0.5d,new Date()));
  RichGaugeReaderPublicMetrics metrics=new RichGaugeReaderPublicMetrics(repository);
  Map<String,Metric<?>> results=new HashMap<String,Metric<?>>();
  for (  Metric<?> metric : metrics.metrics()) {
    results.put(metric.getName(),metric);
  }
  assertTrue(results.containsKey("a.val"));
  assertThat(results.get("a.val").getValue().doubleValue(),equalTo(0.5d));
  assertTrue(results.containsKey("a.avg"));
  assertThat(results.get("a.avg").getValue().doubleValue(),equalTo(0.25d));
  assertTrue(results.containsKey("a.min"));
  assertThat(results.get("a.min").getValue().doubleValue(),equalTo(0.0d));
  assertTrue(results.containsKey("a.max"));
  assertThat(results.get("a.max").getValue().doubleValue(),equalTo(0.5d));
  assertTrue(results.containsKey("a.count"));
  assertThat(results.get("a.count").getValue().longValue(),equalTo(2L));
  assertTrue(results.containsKey("a.alpha"));
  assertThat(results.get("a.alpha").getValue().doubleValue(),equalTo(-1.d));
}
