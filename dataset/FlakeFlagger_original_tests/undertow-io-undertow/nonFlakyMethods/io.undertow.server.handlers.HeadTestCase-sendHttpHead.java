@Test public void sendHttpHead() throws IOException {
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
  HttpHead head=new HttpHead(DefaultServer.getDefaultServerURL() + "/path");
  TestHttpClient client=new TestHttpClient();
  try {
    generateMessage(1);
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals(message,HttpClientUtils.readResponse(result));
    result=client.execute(head);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("",HttpClientUtils.readResponse(result));
    generateMessage(1000);
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals(message,HttpClientUtils.readResponse(result));
    result=client.execute(head);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("",HttpClientUtils.readResponse(result));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
