@Test public void testSystemMetrics() throws Exception {
  SystemPublicMetrics publicMetrics=new SystemPublicMetrics();
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
