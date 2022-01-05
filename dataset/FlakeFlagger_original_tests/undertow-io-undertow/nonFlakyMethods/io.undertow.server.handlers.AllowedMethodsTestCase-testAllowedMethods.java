@Test public void testAllowedMethods() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    final AllowedMethodsHandler handler=new AllowedMethodsHandler(ResponseCodeHandler.HANDLE_200,Methods.POST);
    DefaultServer.setRootHandler(handler);
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(405,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    HttpPost post=new HttpPost(DefaultServer.getDefaultServerURL() + "/path");
    post.setEntity(new StringEntity("foo"));
    result=client.execute(post);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
