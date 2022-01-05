@Test public void testDetectLineLimitEarly() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(2,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  params.setIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH,2);
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  ReadableByteChannel channel=newChannel("GET / HTTP/1.0\r\nHeader: one\r\n\r\n");
  Assert.assertEquals(2,requestParser.fillBuffer(channel));
  Assert.assertNull(requestParser.parse());
  Assert.assertEquals(4,requestParser.fillBuffer(channel));
  try {
    requestParser.parse();
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException expected) {
  }
}
