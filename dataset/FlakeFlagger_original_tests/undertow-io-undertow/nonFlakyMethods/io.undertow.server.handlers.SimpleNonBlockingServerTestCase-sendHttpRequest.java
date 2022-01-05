@Test public void sendHttpRequest() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Header[] header=result.getHeaders("MyHeader");
    Assert.assertEquals("MyValue",header[0].getValue());
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
