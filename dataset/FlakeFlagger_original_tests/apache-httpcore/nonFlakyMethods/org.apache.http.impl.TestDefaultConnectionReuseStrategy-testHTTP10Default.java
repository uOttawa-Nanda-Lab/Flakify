@Test public void testHTTP10Default() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,200,"OK");
  response.addHeader("Content-Length","10");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
