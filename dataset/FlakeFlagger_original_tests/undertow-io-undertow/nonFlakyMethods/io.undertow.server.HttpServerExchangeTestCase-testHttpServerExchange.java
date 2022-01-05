@Test public void testHttpServerExchange() throws IOException {
  final TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/somepath");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("localhost:HTTP/1.1:GET:7777:/somepath:/somepath:",HttpClientUtils.readResponse(result));
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/somepath?a=b");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals("localhost:HTTP/1.1:GET:7777:/somepath:/somepath:a=b",HttpClientUtils.readResponse(result));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
