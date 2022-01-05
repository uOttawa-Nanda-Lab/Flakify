@Test public void testParsingInvalidHeader() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpResponseFactory responseFactory=new DefaultHttpResponseFactory();
  NHttpMessageParser<HttpResponse> responseParser=new DefaultHttpResponseParser(inbuf,null,responseFactory,params);
  ReadableByteChannel channel=newChannel("HTTP/1.0 200 OK\r\nstuff\r\n\r\n");
  responseParser.fillBuffer(channel);
  try {
    responseParser.parse();
    Assert.fail("HttpException should have been thrown");
  }
 catch (  HttpException ex) {
  }
}
