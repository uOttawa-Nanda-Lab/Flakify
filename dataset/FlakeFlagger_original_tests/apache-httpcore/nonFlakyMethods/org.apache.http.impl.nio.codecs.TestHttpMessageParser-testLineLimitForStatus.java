@Test public void testLineLimitForStatus() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  params.setIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH,0);
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  requestParser.fillBuffer(newChannel("GET /whatever HTTP/1.0\r\nHeader: one\r\n\r\n"));
  requestParser.parse();
  requestParser.reset();
  params.setIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH,15);
  requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  try {
    requestParser.fillBuffer(newChannel("GET /loooooooooooooooong HTTP/1.0\r\nHeader: one\r\n\r\n"));
    requestParser.parse();
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException expected) {
  }
}
