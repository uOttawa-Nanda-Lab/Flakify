/** 
 * Tests encoding selection with no qvalue <p/> Also tests a lot of non standard formats for Accept-Encoding to make sure that we are liberal in what we accept
 * @throws IOException
 */
@Test public void testBasicEncodingSelect() throws IOException {
  TestHttpClient client=new TestHttpClient();
  try {
    final EncodingHandler handler=new EncodingHandler(new ContentEncodingRepository().addEncodingHandler("compress",ContentEncodingProvider.IDENTITY,50).addEncodingHandler("bzip",ContentEncodingProvider.IDENTITY,100)).setNext(new HttpHandler(){
      @Override public void handleRequest(      final HttpServerExchange exchange) throws Exception {
        exchange.getResponseSender().send("hi");
      }
    }
);
    DefaultServer.setRootHandler(handler);
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Header[] header=result.getHeaders(HEADER);
    Assert.assertEquals(0,header.length);
    HttpClientUtils.readResponse(result);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.setHeader(Headers.ACCEPT_ENCODING_STRING,"bzip");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    header=result.getHeaders(HEADER);
    Assert.assertEquals("bzip",header[0].getValue());
    HttpClientUtils.readResponse(result);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.setHeader(Headers.ACCEPT_ENCODING_STRING,"bzip compress identity someOtherEndcoding");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    header=result.getHeaders(HEADER);
    Assert.assertEquals("bzip",header[0].getValue());
    HttpClientUtils.readResponse(result);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.setHeader(Headers.ACCEPT_ENCODING_STRING," compress, identity, someOtherEndcoding,  bzip  , ");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    header=result.getHeaders(HEADER);
    Assert.assertEquals("bzip",header[0].getValue());
    HttpClientUtils.readResponse(result);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.setHeader(Headers.ACCEPT_ENCODING_STRING,"boo; compress, identity; someOtherEndcoding,   , ");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    header=result.getHeaders(HEADER);
    Assert.assertEquals("compress",header[0].getValue());
    HttpClientUtils.readResponse(result);
    get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
    get.setHeader(Headers.ACCEPT_ENCODING_STRING,"boo; compress; identity; someOtherEndcoding,   , ");
    result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    header=result.getHeaders(HEADER);
    Assert.assertEquals("compress",header[0].getValue());
    HttpClientUtils.readResponse(result);
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
