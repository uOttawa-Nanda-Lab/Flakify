@Test public void testLoadSharedWithServerShutdown() throws IOException {
  final StringBuilder resultString=new StringBuilder();
  for (int i=0; i < 6; ++i) {
    TestHttpClient client=new TestHttpClient();
    try {
      HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/name");
      HttpResponse result=client.execute(get);
      Assert.assertEquals(200,result.getStatusLine().getStatusCode());
      resultString.append(HttpClientUtils.readResponse(result));
      resultString.append(' ');
    }
  finally {
      client.getConnectionManager().shutdown();
    }
    server1.stop();
    server1.start();
    server2.stop();
    server2.start();
  }
  Assert.assertTrue(resultString.toString().contains("server1"));
  Assert.assertTrue(resultString.toString().contains("server2"));
}
