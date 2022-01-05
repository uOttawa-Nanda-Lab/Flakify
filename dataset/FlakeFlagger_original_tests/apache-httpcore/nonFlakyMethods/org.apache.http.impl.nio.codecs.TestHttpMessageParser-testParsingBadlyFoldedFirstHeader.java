@Test public void testParsingBadlyFoldedFirstHeader() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  requestParser.fillBuffer(newChannel("GET /whatev"));
  HttpRequest request=requestParser.parse();
  Assert.assertNull(request);
  requestParser.fillBuffer(newChannel("er HTTP/1.1\r"));
  request=requestParser.parse();
  Assert.assertNull(request);
  requestParser.fillBuffer(newChannel("\n  Some header: stuff\r\n"));
  request=requestParser.parse();
  Assert.assertNull(request);
  requestParser.fillBuffer(newChannel("   more stuff\r\n"));
  request=requestParser.parse();
  Assert.assertNull(request);
  requestParser.fillBuffer(newChannel("\r\n"));
  request=requestParser.parse();
  Assert.assertNotNull(request);
  Assert.assertEquals("/whatever",request.getRequestLine().getUri());
  Assert.assertEquals(1,request.getAllHeaders().length);
  Assert.assertEquals("stuff more stuff",request.getFirstHeader("Some header").getValue());
}
