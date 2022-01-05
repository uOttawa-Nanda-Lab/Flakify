@Test public void testDeleteRequests() throws IOException {
  message="My HTTP Request!";
  TestHttpClient client=new TestHttpClient();
  HttpDelete delete=new HttpDelete(DefaultServer.getDefaultServerURL() + "/path");
  try {
    for (int i=0; i < 3; ++i) {
      HttpResponse result=client.execute(delete);
      Assert.assertEquals(200,result.getStatusLine().getStatusCode());
      Assert.assertEquals(message,HttpClientUtils.readResponse(result));
    }
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
