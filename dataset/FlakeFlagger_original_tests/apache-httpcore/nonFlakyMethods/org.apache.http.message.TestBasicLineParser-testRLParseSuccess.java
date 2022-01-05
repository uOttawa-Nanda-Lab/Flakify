@Test public void testRLParseSuccess() throws Exception {
  RequestLine requestline=BasicLineParser.parseRequestLine("GET /stuff HTTP/1.1",null);
  Assert.assertEquals("GET /stuff HTTP/1.1",requestline.toString());
  Assert.assertEquals("GET",requestline.getMethod());
  Assert.assertEquals("/stuff",requestline.getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_1,requestline.getProtocolVersion());
  requestline=BasicLineParser.parseRequestLine("  GET    /stuff   HTTP/1.1   ",null);
  Assert.assertEquals("GET /stuff HTTP/1.1",requestline.toString());
  Assert.assertEquals("GET",requestline.getMethod());
  Assert.assertEquals("/stuff",requestline.getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_1,requestline.getProtocolVersion());
  requestline=BasicLineParser.parseRequestLine("\rGET /stuff HTTP/1.1",null);
  Assert.assertEquals("GET",requestline.getMethod());
  Assert.assertEquals("/stuff",requestline.getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_1,requestline.getProtocolVersion());
}
