@Test public void testLotsOfHeadersInResponse() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    for (int i=0; i < COUNT; ++i) {
      Header[] header=result.getHeaders(HEADER + i);
      Assert.assertEquals(MESSAGE + i,header[0].getValue());
    }
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
