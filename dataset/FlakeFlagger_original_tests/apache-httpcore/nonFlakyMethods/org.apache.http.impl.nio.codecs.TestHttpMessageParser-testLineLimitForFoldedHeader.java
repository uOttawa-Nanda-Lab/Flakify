@Test public void testLineLimitForFoldedHeader() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  params.setIntParameter(CoreConnectionPNames.MAX_HEADER_COUNT,2);
  params.setIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH,15);
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  try {
    requestParser.fillBuffer(newChannel("GET / HTTP/1.0\r\nHeader: 9012345\r\n 23456789012345\r\n 23456789012345\r\n 23456789012345\r\n\r\n"));
    requestParser.parse();
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException expected) {
  }
}
