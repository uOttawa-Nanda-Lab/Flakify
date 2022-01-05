@Test public void testBasicPathHanding() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    final PathHandler handler=new PathHandler();
    handler.addPath("a",new RemainingPathHandler("/a"));
    handler.addPath("/aa",new RemainingPathHandler("/aa"));
    handler.addPath("/aa/anotherSubPath",new RemainingPathHandler("/aa/anotherSubPath"));
    final PathHandler sub=new PathHandler();
    handler.addPath("/path",sub);
    sub.addPath("/subpath",new RemainingPathHandler("/subpath"));
    sub.addPath("/",new RemainingPathHandler("/path"));
    DefaultServer.setRootHandler(handler);
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/notamatchingpath");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(404,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/");
    result=client.execute(get);
    Assert.assertEquals(404,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
    runPathTest(client,"/path","/path","");
    runPathTest(client,"/path/a","/path","/a");
    runPathTest(client,"/path/subpath","/subpath","");
    runPathTest(client,"/path/subpath/","/subpath","/");
    runPathTest(client,"/path/subpath/foo","/subpath","/foo");
    runPathTest(client,"/a","/a","");
    runPathTest(client,"/aa/anotherSubPath","/aa/anotherSubPath","");
    runPathTest(client,"/aa/anotherSubPath/bob","/aa/anotherSubPath","/bob");
    runPathTest(client,"/aa?a=b","/aa","",Collections.singletonMap("a","b"));
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
