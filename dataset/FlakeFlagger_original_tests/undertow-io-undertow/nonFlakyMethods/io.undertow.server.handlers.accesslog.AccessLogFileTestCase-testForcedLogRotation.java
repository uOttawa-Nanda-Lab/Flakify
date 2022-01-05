@Test public void testForcedLogRotation() throws IOException, InterruptedException {
  File logFileName=new File(logDirectory,"server.log");
  DefaultAccessLogReceiver logReceiver=new DefaultAccessLogReceiver(DefaultServer.getWorker(),logDirectory,"server");
  CompletionLatchHandler latchHandler;
  DefaultServer.setRootHandler(latchHandler=new CompletionLatchHandler(new AccessLogHandler(HELLO_HANDLER,logReceiver,"Remote address %a Code %s test-header %{i,test-header}",AccessLogFileTestCase.class.getClassLoader())));
  TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.addHeader("test-header","v1");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Hello",HttpClientUtils.readResponse(result));
    latchHandler.await();
    latchHandler.reset();
    logReceiver.awaitWrittenForTest();
    Assert.assertEquals("Remote address 127.0.0.1 Code 200 test-header v1\n",FileUtils.readFile(logFileName));
    logReceiver.rotate();
    logReceiver.awaitWrittenForTest();
    Assert.assertFalse(logFileName.exists());
    File firstLogRotate=new File(logDirectory,"server_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".log");
    Assert.assertEquals("Remote address 127.0.0.1 Code 200 test-header v1\n",FileUtils.readFile(firstLogRotate));
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.addHeader("test-header","v2");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Hello",HttpClientUtils.readResponse(result));
    latchHandler.await();
    latchHandler.reset();
    logReceiver.awaitWrittenForTest();
    Assert.assertEquals("Remote address 127.0.0.1 Code 200 test-header v2\n",FileUtils.readFile(logFileName));
    logReceiver.rotate();
    logReceiver.awaitWrittenForTest();
    Assert.assertFalse(logFileName.exists());
    File secondLogRotate=new File(logDirectory,"server_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-1.log");
    Assert.assertEquals("Remote address 127.0.0.1 Code 200 test-header v2\n",FileUtils.readFile(secondLogRotate));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
