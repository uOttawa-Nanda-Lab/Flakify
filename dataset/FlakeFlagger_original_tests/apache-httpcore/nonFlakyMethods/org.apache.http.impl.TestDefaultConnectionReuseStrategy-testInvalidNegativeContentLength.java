@Test public void testInvalidNegativeContentLength() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Content-Length","-10");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
