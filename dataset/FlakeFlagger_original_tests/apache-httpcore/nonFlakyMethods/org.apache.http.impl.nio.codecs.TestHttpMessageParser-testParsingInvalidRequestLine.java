@Test public void testParsingInvalidRequestLine() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  ReadableByteChannel channel=newChannel("GET garbage\r\n");
  requestParser.fillBuffer(channel);
  try {
    requestParser.parse();
    Assert.fail("HttpException should have been thrown");
  }
 catch (  HttpException ex) {
  }
}
