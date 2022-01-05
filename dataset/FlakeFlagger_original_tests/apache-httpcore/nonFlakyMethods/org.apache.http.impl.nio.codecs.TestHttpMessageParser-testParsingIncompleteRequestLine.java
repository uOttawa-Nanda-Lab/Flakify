@Test public void testParsingIncompleteRequestLine() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  ReadableByteChannel channel=newChannel("GET /whatever HTTP/1.0");
  requestParser.fillBuffer(channel);
  requestParser.fillBuffer(channel);
  HttpRequest request=requestParser.parse();
  Assert.assertNotNull(request);
  Assert.assertEquals(HttpVersion.HTTP_1_0,request.getRequestLine().getProtocolVersion());
}
