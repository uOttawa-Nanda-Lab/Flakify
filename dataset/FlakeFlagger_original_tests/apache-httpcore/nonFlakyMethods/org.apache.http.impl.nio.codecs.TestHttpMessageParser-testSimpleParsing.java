@Test public void testSimpleParsing() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();
  NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);
  requestParser.fillBuffer(newChannel("GET /whatever HTTP/1.1\r\nSome header: stuff\r\n\r\n"));
  HttpRequest request=requestParser.parse();
  Assert.assertNotNull(request);
  Assert.assertEquals("/whatever",request.getRequestLine().getUri());
  Assert.assertEquals(1,request.getAllHeaders().length);
}
