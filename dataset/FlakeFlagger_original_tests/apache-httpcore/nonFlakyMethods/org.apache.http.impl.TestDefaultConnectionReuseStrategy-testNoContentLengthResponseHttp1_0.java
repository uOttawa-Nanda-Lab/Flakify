@Test public void testNoContentLengthResponseHttp1_0() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,200,"OK");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
