@Test public void testConnectionTokens1() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","yadda, cLOSe, dumdy");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
