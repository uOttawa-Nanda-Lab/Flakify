@Test public void testConnectionTokens6() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","");
  response.addHeader("Proxy-Connection","close");
  Assert.assertTrue(reuseStrategy.keepAlive(response,context));
}
