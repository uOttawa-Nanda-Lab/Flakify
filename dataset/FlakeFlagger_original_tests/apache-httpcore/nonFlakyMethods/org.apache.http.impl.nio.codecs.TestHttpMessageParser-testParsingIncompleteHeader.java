@Test public void testParsingIncompleteHeader() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  ReadableByteChannel channel=newChannel("GET /whatever HTTP/1.0\r\nHeader: whatever");
  requestParser.fillBuffer(channel);
  requestParser.fillBuffer(channel);
  HttpRequest request=requestParser.parse();
  Assert.assertNotNull(request);
  Assert.assertEquals(1,request.getAllHeaders().length);
  Assert.assertEquals("whatever",request.getFirstHeader("Header").getValue());
}
