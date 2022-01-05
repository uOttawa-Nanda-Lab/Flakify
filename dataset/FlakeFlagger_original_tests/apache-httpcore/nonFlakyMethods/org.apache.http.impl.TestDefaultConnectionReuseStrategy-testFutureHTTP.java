@Test public void testFutureHTTP() throws Exception {
  HttpResponse response=new BasicHttpResponse(new HttpVersion(3,45),200,"OK");
  response.addHeader("Content-Length","10");
  Assert.assertTrue(reuseStrategy.keepAlive(response,context));
}
