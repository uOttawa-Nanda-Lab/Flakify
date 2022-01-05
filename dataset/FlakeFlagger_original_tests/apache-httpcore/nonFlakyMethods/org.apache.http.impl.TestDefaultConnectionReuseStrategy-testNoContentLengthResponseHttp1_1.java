@Test public void testNoContentLengthResponseHttp1_1() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  Assert.assertFalse(reuseStrategy.keepAlive(response,context));
}
