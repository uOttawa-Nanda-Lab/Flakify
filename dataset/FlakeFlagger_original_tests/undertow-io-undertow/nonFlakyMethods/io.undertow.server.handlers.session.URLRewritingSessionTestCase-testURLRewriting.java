@Test public void testURLRewriting() throws IOException {
  TestHttpClient client=new TestHttpClient();
  client.setCookieStore(new BasicCookieStore());
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/notamatchingpath");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    String url=HttpClientUtils.readResponse(result);
    Header[] header=result.getHeaders(COUNT);
    Assert.assertEquals("0",header[0].getValue());
    get=new HttpGet(url);
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    url=HttpClientUtils.readResponse(result);
    header=result.getHeaders(COUNT);
    Assert.assertEquals("1",header[0].getValue());
    get=new HttpGet(url);
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    url=HttpClientUtils.readResponse(result);
    header=result.getHeaders(COUNT);
    Assert.assertEquals("2",header[0].getValue());
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
