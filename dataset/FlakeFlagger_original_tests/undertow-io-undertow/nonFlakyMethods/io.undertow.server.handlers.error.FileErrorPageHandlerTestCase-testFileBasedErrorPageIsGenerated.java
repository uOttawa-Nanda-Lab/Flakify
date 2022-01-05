@Test public void testFileBasedErrorPageIsGenerated() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    final FileErrorPageHandler handler=new FileErrorPageHandler(new File(getClass().getResource("errorpage.html").getFile()),404);
    DefaultServer.setRootHandler(handler);
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(404,result.getStatusLine().getStatusCode());
    final String response=HttpClientUtils.readResponse(result);
    Assert.assertTrue(response,response.contains("Custom Error Page"));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
