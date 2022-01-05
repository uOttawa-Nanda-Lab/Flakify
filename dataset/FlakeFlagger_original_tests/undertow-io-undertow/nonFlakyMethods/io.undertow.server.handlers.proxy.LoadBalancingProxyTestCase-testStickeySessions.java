@Test public void testStickeySessions() throws IOException {
  int expected=0;
  TestHttpClient client=new TestHttpClient();
  try {
    for (int i=0; i < 6; ++i) {
      HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/session");
      get.addHeader("Connection","close");
      HttpResponse result=client.execute(get);
      Assert.assertEquals(200,result.getStatusLine().getStatusCode());
      int count=Integer.parseInt(HttpClientUtils.readResponse(result));
      Assert.assertEquals(expected++,count);
    }
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
