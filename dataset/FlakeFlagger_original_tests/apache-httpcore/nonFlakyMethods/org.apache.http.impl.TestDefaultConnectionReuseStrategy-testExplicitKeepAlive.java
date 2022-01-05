@Test public void testExplicitKeepAlive() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,200,"OK");
  response.addHeader("Content-Length","10");
  response.addHeader("Connection","keep-alive");
  Assert.assertTrue(reuseStrategy.keepAlive(response,context));
}
