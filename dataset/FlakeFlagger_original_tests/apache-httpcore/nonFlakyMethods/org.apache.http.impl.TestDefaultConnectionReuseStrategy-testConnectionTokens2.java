@Test public void testConnectionTokens2() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","yadda, kEEP-alive, dumdy");
  Assert.assertTrue(reuseStrategy.keepAlive(response,context));
}
