@Test public void testRedirectHandler() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path/a");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    Assert.assertEquals("/target/path/a",message);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/aabc");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    Assert.assertEquals("/target/matched/aab",message);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/somePath/aabc");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    Assert.assertEquals("/target/matched/aab",message);
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
