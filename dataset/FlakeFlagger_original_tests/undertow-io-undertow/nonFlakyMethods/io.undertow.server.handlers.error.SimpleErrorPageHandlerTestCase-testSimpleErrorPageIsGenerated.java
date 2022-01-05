@Test public void testSimpleErrorPageIsGenerated() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    final SimpleErrorPageHandler handler=new SimpleErrorPageHandler();
    DefaultServer.setRootHandler(handler);
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(404,result.getStatusLine().getStatusCode());
    final String response=HttpClientUtils.readResponse(result);
    Assert.assertTrue(response,response.contains(StatusCodes.NOT_FOUND_STRING));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
