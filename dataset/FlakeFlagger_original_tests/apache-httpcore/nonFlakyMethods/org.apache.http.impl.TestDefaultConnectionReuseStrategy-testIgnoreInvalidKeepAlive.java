@Test public void testIgnoreInvalidKeepAlive() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,200,"OK");
  response.addHeader("Connection","keep-alive");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
