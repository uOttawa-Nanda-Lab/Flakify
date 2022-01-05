@Test public void testMultipleContentLength() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Content-Length","10");
  response.addHeader("Content-Length","11");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
