@Test public void testBasicPathBasedCaching() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    for (int i=1; i <= 5; ++i) {
      HttpResponse result=client.execute(get);
      Assert.assertEquals(200,result.getStatusLine().getStatusCode());
      Assert.assertEquals("Response " + i,HttpClientUtils.readResponse(result));
    }
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Response 5",HttpClientUtils.readResponse(result));
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Response 5",HttpClientUtils.readResponse(result));
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Response 5",HttpClientUtils.readResponse(result));
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path2");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("Response 6",HttpClientUtils.readResponse(result));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
