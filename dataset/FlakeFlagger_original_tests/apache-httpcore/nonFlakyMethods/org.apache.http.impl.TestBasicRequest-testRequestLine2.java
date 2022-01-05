@Test public void testRequestLine2() throws Exception {
  HttpRequest request=new BasicHttpRequest("GET","/stuff",HttpVersion.HTTP_1_0);
  Assert.assertEquals("GET",request.getRequestLine().getMethod());
  Assert.assertEquals("/stuff",request.getRequestLine().getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_0,request.getRequestLine().getProtocolVersion());
}
