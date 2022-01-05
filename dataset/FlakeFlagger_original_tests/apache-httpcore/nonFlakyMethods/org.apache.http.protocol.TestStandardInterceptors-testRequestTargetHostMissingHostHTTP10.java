@Test public void testRequestTargetHostMissingHostHTTP10() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/",HttpVersion.HTTP_1_0);
  RequestTargetHost interceptor=new RequestTargetHost();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.TARGET_HOST);
  Assert.assertNull(header);
}
