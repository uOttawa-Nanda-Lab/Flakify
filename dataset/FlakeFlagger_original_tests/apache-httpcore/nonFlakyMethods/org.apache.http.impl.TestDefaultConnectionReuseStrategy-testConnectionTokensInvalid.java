@Test public void testConnectionTokensInvalid() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","keep-alive=true");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
