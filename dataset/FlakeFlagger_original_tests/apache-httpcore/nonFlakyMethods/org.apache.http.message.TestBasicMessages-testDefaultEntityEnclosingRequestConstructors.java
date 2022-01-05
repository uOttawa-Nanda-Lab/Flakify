@Test public void testDefaultEntityEnclosingRequestConstructors(){
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("GET","/");
  Assert.assertNotNull(request.getProtocolVersion());
  Assert.assertEquals("GET",request.getRequestLine().getMethod());
  Assert.assertEquals("/",request.getRequestLine().getUri());
  request=new BasicHttpEntityEnclosingRequest("GET","/",HttpVersion.HTTP_1_0);
  Assert.assertEquals(HttpVersion.HTTP_1_0,request.getProtocolVersion());
  Assert.assertEquals("GET",request.getRequestLine().getMethod());
  Assert.assertEquals("/",request.getRequestLine().getUri());
}
