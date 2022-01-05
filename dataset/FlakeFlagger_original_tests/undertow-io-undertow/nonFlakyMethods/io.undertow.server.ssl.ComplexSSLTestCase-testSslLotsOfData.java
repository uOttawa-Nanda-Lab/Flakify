@Test public void testSslLotsOfData() throws IOException, GeneralSecurityException, URISyntaxException {
  DefaultServer.setRootHandler(new HttpHandler(){
    @Override public void handleRequest(    HttpServerExchange exchange) throws Exception {
      if (exchange.isInIoThread()) {
        exchange.dispatch(this);
        return;
      }
      exchange.startBlocking();
      ByteArrayOutputStream out=new ByteArrayOutputStream();
      HttpServerConnection connection=(HttpServerConnection)exchange.getConnection();
      byte[] buf=new byte[100];
      int res=0;
      while ((res=exchange.getInputStream().read(buf)) > 0) {
        out.write(buf,0,res);
      }
      exchange.getOutputStream().write(out.toByteArray());
    }
  }
);
  DefaultServer.startSSLServer();
  TestHttpClient client=new TestHttpClient();
  client.setSSLContext(DefaultServer.getClientSSLContext());
  try {
    generateMessage(1000000);
    HttpPost post=new HttpPost(DefaultServer.getDefaultServerSSLAddress());
    post.setEntity(new StringEntity(message));
    HttpResponse resultList=client.execute(post);
    Assert.assertEquals(200,resultList.getStatusLine().getStatusCode());
    String response=HttpClientUtils.readResponse(resultList);
    Assert.assertEquals(message,response);
    generateMessage(100000);
    post=new HttpPost(DefaultServer.getDefaultServerSSLAddress());
    post.setEntity(new StringEntity(message));
    resultList=client.execute(post);
    Assert.assertEquals(200,resultList.getStatusLine().getStatusCode());
    response=HttpClientUtils.readResponse(resultList);
    Assert.assertEquals(message,response);
  }
  finally {
    client.getConnectionManager().shutdown();
    DefaultServer.stopSSLServer();
  }
}
