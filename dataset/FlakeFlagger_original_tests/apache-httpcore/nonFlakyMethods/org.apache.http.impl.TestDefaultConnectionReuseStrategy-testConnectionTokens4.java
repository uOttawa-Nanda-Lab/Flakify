@Test public void testConnectionTokens4() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","yadda, close, dumdy");
  response.addHeader("Proxy-Connection","keep-alive");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
