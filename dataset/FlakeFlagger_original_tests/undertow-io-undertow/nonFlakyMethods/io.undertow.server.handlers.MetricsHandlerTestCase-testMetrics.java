@Test public void testMetrics() throws IOException, InterruptedException {
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
  TestHttpClient client=new TestHttpClient();
  try {
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Hello",HttpClientUtils.readResponse(result));
    latchHandler.await();
    latchHandler.reset();
    MetricsHandler.MetricResult metrics=metricsHandler.getMetrics();
    Assert.assertEquals(1,metrics.getTotalRequests());
    Assert.assertTrue(metrics.getMaxRequestTime() > 0);
    Assert.assertEquals(metrics.getMinRequestTime(),metrics.getMaxRequestTime());
    Assert.assertEquals(metrics.getMaxRequestTime(),metrics.getTotalRequestTime());
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Hello",HttpClientUtils.readResponse(result));
    latchHandler.await();
    latchHandler.reset();
    metrics=metricsHandler.getMetrics();
    Assert.assertEquals(2,metrics.getTotalRequests());
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
