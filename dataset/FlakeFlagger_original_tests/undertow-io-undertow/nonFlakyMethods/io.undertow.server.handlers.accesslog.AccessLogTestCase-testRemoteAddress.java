@Test public void testRemoteAddress() throws IOException, InterruptedException {
  latch=new CountDownLatch(1);
  DefaultServer.setRootHandler(new AccessLogHandler(HELLO_HANDLER,RECIEVER,"Remote address %a Code %s test-header %{i,test-header}",AccessLogFileTestCase.class.getClassLoader()));
  TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.addHeader("test-header","test-value");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Hello",HttpClientUtils.readResponse(result));
    latch.await(10,TimeUnit.SECONDS);
    Assert.assertEquals("Remote address 127.0.0.1 Code 200 test-header test-value",message);
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
