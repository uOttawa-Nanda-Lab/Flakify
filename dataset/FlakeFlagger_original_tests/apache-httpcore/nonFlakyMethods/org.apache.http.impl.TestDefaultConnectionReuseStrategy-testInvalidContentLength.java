@Test public void testInvalidContentLength() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Content-Length","crap");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
