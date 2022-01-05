@Test public void testConnectionTokens5() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","yadda, dumdy");
  response.addHeader("Proxy-Connection","close");
  Assert.assertTrue(reuseStrategy.keepAlive(response,context));
}
