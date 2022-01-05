@Test public void testConnectionTokens3() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","yadda, keep-alive, close, dumdy");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
