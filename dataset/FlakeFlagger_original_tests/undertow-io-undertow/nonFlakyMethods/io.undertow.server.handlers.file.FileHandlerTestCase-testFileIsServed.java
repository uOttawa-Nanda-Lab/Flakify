@Test public void testFileIsServed() throws IOException, URISyntaxException {
  TestHttpClient client=new TestHttpClient();
  File rootPath=new File(getClass().getResource("page.html").toURI()).getParentFile();
  try {
    DefaultServer.setRootHandler(new CanonicalPathHandler().setNext(new PathHandler().addPath("/path",new ResourceHandler().setResourceManager(new FileResourceManager(rootPath,10485760)).setDirectoryListingEnabled(true))));
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path/page.html");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    final String response=HttpClientUtils.readResponse(result);
    Header[] headers=result.getHeaders("Content-Type");
    Assert.assertEquals("text/html",headers[0].getValue());
    Assert.assertTrue(response,response.contains("A web page"));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
