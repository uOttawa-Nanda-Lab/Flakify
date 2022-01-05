@Test public void testExplicitClose() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  response.addHeader("Connection","close");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
