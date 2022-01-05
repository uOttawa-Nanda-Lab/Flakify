@Test public void metricEndpointCustomPublicMetrics(){
  load(CustomPublicMetricsConfig.class,EndpointAutoConfiguration.class);
  MetricsEndpoint endpoint=this.context.getBean(MetricsEndpoint.class);
  Map<String,Object> metrics=endpoint.invoke();
  assertTrue(metrics.containsKey("foo"));
  assertTrue(metrics.containsKey("mem"));
  assertTrue(metrics.containsKey("heap.used"));
}
