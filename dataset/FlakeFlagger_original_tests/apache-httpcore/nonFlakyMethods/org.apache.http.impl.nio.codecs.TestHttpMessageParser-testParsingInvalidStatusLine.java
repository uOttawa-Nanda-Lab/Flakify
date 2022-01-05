@Test public void testParsingInvalidStatusLine() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpResponseFactory responseFactory=new DefaultHttpResponseFactory();
  NHttpMessageParser<HttpResponse> responseParser=new DefaultHttpResponseParser(inbuf,null,responseFactory,params);
  ReadableByteChannel channel=newChannel("HTTP 200 OK\r\n");
  responseParser.fillBuffer(channel);
  try {
    responseParser.parse();
    Assert.fail("HttpException should have been thrown");
  }
 catch (  HttpException ex) {
  }
}
