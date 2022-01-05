@Test public void testBrokenConnectionDirective1() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,200,"OK");
  response.addHeader("Content-Length","10");
  response.addHeader("Connection","keep--alive");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
