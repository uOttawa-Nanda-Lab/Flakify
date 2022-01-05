@Test public void testMetrics() throws Exception {
  InMemoryMetricRepository repository=new InMemoryMetricRepository();
  repository.set(new Metric<Double>("a",0.5,new Date()));
  VanillaPublicMetrics publicMetrics=new VanillaPublicMetrics(repository);
  Map<String,Metric<?>> results=new HashMap<String,Metric<?>>();
  for (  Metric<?> metric : publicMetrics.metrics()) {
    results.put(metric.getName(),metric);
  }
  assertTrue(results.containsKey("mem"));
  assertTrue(results.containsKey("mem.free"));
  assertThat(results.get("a").getValue().doubleValue(),equalTo(0.5));
}
