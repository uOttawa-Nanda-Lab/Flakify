@Test public void testChunkedContent() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Transfer-Encoding","chunked");
  Assert.assertTrue(reuseStrategy.keepAlive(response,context));
}
