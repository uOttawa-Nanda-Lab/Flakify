@Test public void testConstructor(){
  RequestLine requestline=new BasicRequestLine("GET","/stuff",HttpVersion.HTTP_1_1);
  Assert.assertEquals("GET",requestline.getMethod());
  Assert.assertEquals("/stuff",requestline.getUri());
  Assert.assertEquals(HttpVersion.HTTP_1_1,requestline.getProtocolVersion());
}
