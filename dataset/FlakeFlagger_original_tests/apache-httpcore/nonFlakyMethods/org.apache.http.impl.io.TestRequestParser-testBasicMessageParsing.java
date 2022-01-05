@Test public void testBasicMessageParsing() throws Exception {
  String s="GET / HTTP/1.1\r\n" + "Host: localhost\r\n" + "User-Agent: whatever\r\n"+ "Cookie: c1=stuff\r\n"+ "\r\n";
  SessionInputBuffer inbuffer=new SessionInputBufferMock(s,"US-ASCII");
  HttpRequestParser parser=new HttpRequestParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpRequestFactory(),new BasicHttpParams());
  HttpRequest httprequest=parser.parse();
  RequestLine reqline=httprequest.getRequestLine();
  Assert.assertNotNull(reqline);
  Assert.assertEquals("GET",reqline.getMethod());
  Assert.assertEquals("/",reqline.getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_1,reqline.getProtocolVersion());
  Header[] headers=httprequest.getAllHeaders();
  Assert.assertEquals(3,headers.length);
}
