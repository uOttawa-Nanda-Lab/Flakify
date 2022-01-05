@Test public void testMessageParsingTimeout() throws Exception {
  String s="GET \000/ HTTP/1.1\r\000\n" + "Host: loca\000lhost\r\n" + "User-Agent: whatever\r\n"+ "Coo\000kie: c1=stuff\r\n"+ "\000\r\n";
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new TimeoutByteArrayInputStream(s.getBytes("US-ASCII")),16);
  HttpRequestParser parser=new HttpRequestParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpRequestFactory(),new BasicHttpParams());
  int timeoutCount=0;
  HttpRequest httprequest=null;
  for (int i=0; i < 10; i++) {
    try {
      httprequest=parser.parse();
      break;
    }
 catch (    InterruptedIOException ex) {
      timeoutCount++;
    }
  }
  Assert.assertNotNull(httprequest);
  Assert.assertEquals(5,timeoutCount);
  @SuppressWarnings("null") RequestLine reqline=httprequest.getRequestLine();
  Assert.assertNotNull(reqline);
  Assert.assertEquals("GET",reqline.getMethod());
  Assert.assertEquals("/",reqline.getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_1,reqline.getProtocolVersion());
  Header[] headers=httprequest.getAllHeaders();
  Assert.assertEquals(3,headers.length);
}
