@Test public void testRequestTargetHostMissingHostHTTP11() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  RequestTargetHost interceptor=new RequestTargetHost();
  try {
    interceptor.process(request,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
