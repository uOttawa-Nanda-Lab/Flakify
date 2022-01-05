@Test public void exposesMetrics(){
  List<Metric<?>> metrics=new ArrayList<Metric<?>>();
  metrics.add(mock(Metric.class));
  metrics.add(mock(Metric.class));
  MetricReader reader=mock(MetricReader.class);
  given(reader.findAll()).willReturn(metrics);
  MetricReaderPublicMetrics publicMetrics=new MetricReaderPublicMetrics(reader);
  assertEquals(metrics,publicMetrics.metrics());
}
