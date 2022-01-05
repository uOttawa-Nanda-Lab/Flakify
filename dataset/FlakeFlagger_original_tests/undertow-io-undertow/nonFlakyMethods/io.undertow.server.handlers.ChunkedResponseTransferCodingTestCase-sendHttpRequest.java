@Test public void sendHttpRequest() throws IOException {
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
  TestHttpClient client=new TestHttpClient();
  try {
    generateMessage(0);
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals(message,HttpClientUtils.readResponse(result));
    generateMessage(1);
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals(message,HttpClientUtils.readResponse(result));
    generateMessage(1000);
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals(message,HttpClientUtils.readResponse(result));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
