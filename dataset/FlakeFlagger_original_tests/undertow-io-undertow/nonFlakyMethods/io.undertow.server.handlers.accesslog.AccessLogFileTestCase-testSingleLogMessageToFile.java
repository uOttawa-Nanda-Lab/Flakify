@Test public void testSingleLogMessageToFile() throws IOException, InterruptedException {
  File directory=logDirectory;
  File logFileName=new File(directory,"server1.log");
  CompletionLatchHandler latchHandler;
  DefaultAccessLogReceiver logReceiver=new DefaultAccessLogReceiver(DefaultServer.getWorker(),directory,"server1");
  DefaultServer.setRootHandler(latchHandler=new CompletionLatchHandler(new AccessLogHandler(HELLO_HANDLER,logReceiver,"Remote address %a Code %s test-header %{i,test-header}",AccessLogFileTestCase.class.getClassLoader())));
  TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.addHeader("test-header","single-val");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Hello",HttpClientUtils.readResponse(result));
    latchHandler.await();
    logReceiver.awaitWrittenForTest();
    Assert.assertEquals("Remote address 127.0.0.1 Code 200 test-header single-val\n",FileUtils.readFile(logFileName));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
