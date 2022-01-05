@Test public void simpleGracefulShutdownTestCase() throws IOException, InterruptedException {
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
  TestHttpClient client=new TestHttpClient();
  try {
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    shutdown.shutdown();
    result=client.execute(get);
    Assert.assertEquals(503,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    shutdown.start();
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    CountDownLatch latch=new CountDownLatch(1);
    latch2.set(latch);
    latch1.set(new CountDownLatch(1));
    Thread t=new Thread(new RequestTask());
    t.start();
    latch1.get().await();
    shutdown.shutdown();
    Assert.assertFalse(shutdown.awaitShutdown(10));
    latch.countDown();
    Assert.assertTrue(shutdown.awaitShutdown(10000));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
