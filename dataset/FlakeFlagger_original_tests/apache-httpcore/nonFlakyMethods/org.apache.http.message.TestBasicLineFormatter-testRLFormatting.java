@Test public void testRLFormatting() throws Exception {
  RequestLine requestline=new BasicRequestLine("GET","/stuff",HttpVersion.HTTP_1_1);
  String s=BasicLineFormatter.formatRequestLine(requestline,null);
  Assert.assertEquals("GET /stuff HTTP/1.1",s);
}
