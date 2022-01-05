@Test public void testMaxHeaderCount() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  params.setIntParameter(CoreConnectionPNames.MAX_HEADER_COUNT,2);
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  requestParser.fillBuffer(newChannel("GET /whatever HTTP/1.0\r\nHeader: one\r\nHeader: two\r\n\r\n"));
  requestParser.parse();
  requestParser.reset();
  try {
    requestParser.fillBuffer(newChannel("GET /whatever HTTP/1.0\r\nHeader: one\r\nHeader: two\r\nHeader: three\r\n\r\n"));
    requestParser.parse();
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException expected) {
  }
}
