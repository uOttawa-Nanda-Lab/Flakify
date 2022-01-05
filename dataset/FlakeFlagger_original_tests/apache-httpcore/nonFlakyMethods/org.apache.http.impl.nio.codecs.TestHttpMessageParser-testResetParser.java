@Test public void testResetParser() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  ReadableByteChannel channel=newChannel("GET /whatever HTTP/1.0\r\nHeader: one\r\n\r\n");
  requestParser.fillBuffer(channel);
  HttpRequest request=requestParser.parse();
  Assert.assertNotNull(request);
  Assert.assertEquals(1,request.getAllHeaders().length);
  Assert.assertEquals("one",request.getFirstHeader("Header").getValue());
  requestParser.reset();
  channel=newChannel("GET /whatever HTTP/1.0\r\nHeader: two\r\n\r\n");
  requestParser.fillBuffer(channel);
  request=requestParser.parse();
  Assert.assertNotNull(request);
  Assert.assertEquals(1,request.getAllHeaders().length);
  Assert.assertEquals("two",request.getFirstHeader("Header").getValue());
}
