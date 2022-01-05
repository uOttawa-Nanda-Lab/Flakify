@Test public void testMaxRequestHeaderSize() throws IOException {
  OptionMap existing=DefaultServer.getUndertowOptions();
  final TestHttpClient client=new TestHttpClient();
  try {
    HttpPost post=new HttpPost(DefaultServer.getDefaultServerURL() + "/notamatchingpath");
    post.setEntity(new StringEntity(A_MESSAGE));
    post.addHeader(Headers.CONNECTION_STRING,"close");
    HttpResponse result=client.execute(post);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    OptionMap maxSize=OptionMap.create(UndertowOptions.MAX_HEADER_SIZE,10);
    DefaultServer.setUndertowOptions(maxSize);
    try {
      HttpResponse response=client.execute(post);
      HttpClientUtils.readResponse(response);
      if (DefaultServer.isProxy() || DefaultServer.isAjp()) {
        Assert.assertEquals(500,response.getStatusLine().getStatusCode());
      }
 else {
        Assert.fail("request should have been too big");
      }
    }
 catch (    IOException e) {
    }
    maxSize=OptionMap.create(UndertowOptions.MAX_HEADER_SIZE,1000);
    DefaultServer.setUndertowOptions(maxSize);
    result=client.execute(post);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
  }
  finally {
    DefaultServer.setUndertowOptions(existing);
    client.getConnectionManager().shutdown();
  }
}
