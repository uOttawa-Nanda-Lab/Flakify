@Test public void testDefaultRequestConstructors(){
  HttpRequest request=new BasicHttpRequest("WHATEVER","/");
  Assert.assertNotNull(request.getProtocolVersion());
  Assert.assertEquals("WHATEVER",request.getRequestLine().getMethod());
  Assert.assertEquals("/",request.getRequestLine().getUri());
  request=new BasicHttpRequest("GET","/",HttpVersion.HTTP_1_0);
  Assert.assertEquals(HttpVersion.HTTP_1_0,request.getProtocolVersion());
  Assert.assertEquals("GET",request.getRequestLine().getMethod());
  Assert.assertEquals("/",request.getRequestLine().getUri());
  try {
    new BasicHttpRequest(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new BasicHttpRequest("GET",null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new BasicHttpRequest(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
