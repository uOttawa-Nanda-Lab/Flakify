@Test public void testRequestLine() throws Exception {
  HttpRequest request=new BasicHttpRequest("GET","/stuff");
  request.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_0);
  Assert.assertEquals("GET",request.getRequestLine().getMethod());
  Assert.assertEquals("/stuff",request.getRequestLine().getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_0,request.getRequestLine().getProtocolVersion());
}
