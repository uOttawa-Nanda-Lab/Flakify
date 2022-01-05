@Test public void testHTTP11Default() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  response.addHeader("Content-Length","10");
  Assert.assertTrue(reuseStrategy.keepAlive(response,context));
}
