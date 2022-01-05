@Test public void testSystemMetrics() throws Exception {
  InMemoryMetricRepository repository=new InMemoryMetricRepository();
  repository.set(new Metric<Double>("a",0.5,new Date()));
  VanillaPublicMetrics publicMetrics=new VanillaPublicMetrics(repository);
  Map<String,Metric<?>> results=new HashMap<String,Metric<?>>();
  for (  Metric<?> metric : publicMetrics.metrics()) {
    results.put(metric.getName(),metric);
  }
  assertTrue(results.containsKey("mem"));
  assertTrue(results.containsKey("mem.free"));
  assertTrue(results.containsKey("processors"));
  assertTrue(results.containsKey("uptime"));
  assertTrue(results.containsKey("heap.committed"));
  assertTrue(results.containsKey("heap.init"));
  assertTrue(results.containsKey("heap.used"));
  assertTrue(results.containsKey("heap"));
  assertTrue(results.containsKey("threads.peak"));
  assertTrue(results.containsKey("threads.daemon"));
  assertTrue(results.containsKey("threads"));
  assertTrue(results.containsKey("classes.loaded"));
  assertTrue(results.containsKey("classes.unloaded"));
  assertTrue(results.containsKey("classes"));
}
