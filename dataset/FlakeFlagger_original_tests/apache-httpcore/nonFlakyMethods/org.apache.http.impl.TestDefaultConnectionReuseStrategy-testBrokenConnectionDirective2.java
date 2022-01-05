@Test public void testBrokenConnectionDirective2() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,200,"OK");
  response.addHeader("Content-Length","10");
  response.addHeader("Connection",null);
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
