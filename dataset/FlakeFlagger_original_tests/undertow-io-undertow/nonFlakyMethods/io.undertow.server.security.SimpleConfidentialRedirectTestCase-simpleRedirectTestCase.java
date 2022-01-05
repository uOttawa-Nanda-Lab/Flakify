@Test public void simpleRedirectTestCase() throws IOException, GeneralSecurityException {
  DefaultServer.startSSLServer();
  HttpHandler current=new HttpHandler(){
    @Override public void handleRequest(    final HttpServerExchange exchange) throws Exception {
      exchange.getResponseHeaders().put(HttpString.tryFromString("scheme"),exchange.getRequestScheme());
      exchange.endExchange();
    }
  }
;
  current=new SinglePortConfidentialityHandler(current,DefaultServer.getHostSSLPort("default"));
  DefaultServer.setRootHandler(current);
  TestHttpClient client=new TestHttpClient();
  client.setSSLContext(DefaultServer.getClientSSLContext());
  try {
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL());
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    Header[] header=result.getHeaders("scheme");
    Assert.assertEquals("https",header[0].getValue());
  }
  finally {
    client.getConnectionManager().shutdown();
    DefaultServer.stopSSLServer();
  }
}
