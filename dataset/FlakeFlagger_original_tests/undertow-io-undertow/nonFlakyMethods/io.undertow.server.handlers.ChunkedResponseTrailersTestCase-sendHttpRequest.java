@Test public void sendHttpRequest() throws Exception {
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
  TestHttpClient client=new TestHttpClient();
  final AtomicReference<ChunkedInputStream> stream=new AtomicReference<ChunkedInputStream>();
  client.addResponseInterceptor(new HttpResponseInterceptor(){
    public void process(    final HttpResponse response,    final HttpContext context) throws IOException {
      HttpEntity entity=response.getEntity();
      if (entity != null) {
        InputStream instream=entity.getContent();
        if (instream instanceof ChunkedInputStream) {
          stream.set(((ChunkedInputStream)instream));
        }
      }
    }
  }
);
  try {
    generateMessage(1);
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals(message,HttpClientUtils.readResponse(result));
    Header[] footers=stream.get().getFooters();
    Assert.assertEquals(2,footers.length);
    for (    final Header header : footers) {
      if (header.getName().equals("foo")) {
        Assert.assertEquals("fooVal",header.getValue());
      }
 else       if (header.getName().equals("bar")) {
        Assert.assertEquals("barVal",header.getValue());
      }
 else {
        Assert.fail("Unknown header" + header);
      }
    }
    generateMessage(1000);
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Assert.assertEquals(message,HttpClientUtils.readResponse(result));
    footers=stream.get().getFooters();
    Assert.assertEquals(2,footers.length);
    for (    final Header header : footers) {
      if (header.getName().equals("foo")) {
        Assert.assertEquals("fooVal",header.getValue());
      }
 else       if (header.getName().equals("bar")) {
        Assert.assertEquals("barVal",header.getValue());
      }
 else {
        Assert.fail("Unknown header" + header);
      }
    }
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
