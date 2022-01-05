@Test public void metricEndpointsHasSystemMetricsByDefault(){
  load(EndpointAutoConfiguration.class);
  MetricsEndpoint endpoint=this.context.getBean(MetricsEndpoint.class);
  Map<String,Object> metrics=endpoint.invoke();
  assertTrue(metrics.containsKey("mem"));
  assertTrue(metrics.containsKey("heap.used"));
}
