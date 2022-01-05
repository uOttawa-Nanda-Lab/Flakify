@Test public void complexSSLTestCase() throws IOException, GeneralSecurityException, URISyntaxException {
  final PathHandler pathHandler=new PathHandler();
  File rootPath=new File(FileHandlerTestCase.class.getResource("page.html").toURI()).getParentFile();
  final NameVirtualHostHandler virtualHostHandler=new NameVirtualHostHandler();
  HttpHandler root=virtualHostHandler;
  root=new SimpleErrorPageHandler(root);
  root=new CanonicalPathHandler(root);
  virtualHostHandler.addHost("default-host",pathHandler);
  virtualHostHandler.setDefaultHandler(pathHandler);
  pathHandler.addPath("/",new ResourceHandler().setResourceManager(new FileResourceManager(rootPath,10485760)).setDirectoryListingEnabled(true));
  DefaultServer.setRootHandler(root);
  DefaultServer.startSSLServer();
  TestHttpClient client=new TestHttpClient();
  client.setSSLContext(DefaultServer.getClientSSLContext());
  try {
    HttpGet getFileList=new HttpGet(DefaultServer.getDefaultServerSSLAddress());
    HttpResponse resultList=client.execute(getFileList);
    Assert.assertEquals(200,resultList.getStatusLine().getStatusCode());
    String responseList=HttpClientUtils.readResponse(resultList);
    Assert.assertTrue(responseList,responseList.contains("page.html"));
    Header[] headersList=resultList.getHeaders("Content-Type");
    Assert.assertEquals("text/html",headersList[0].getValue());
    HttpGet getFile=new HttpGet(DefaultServer.getDefaultServerSSLAddress() + "/page.html");
    HttpResponse result=client.execute(getFile);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    String response=HttpClientUtils.readResponse(result);
    Header[] headers=result.getHeaders("Content-Type");
    Assert.assertEquals("text/html",headers[0].getValue());
    Assert.assertTrue(response,response.contains("A web page"));
  }
  finally {
    client.getConnectionManager().shutdown();
    DefaultServer.stopSSLServer();
  }
}
