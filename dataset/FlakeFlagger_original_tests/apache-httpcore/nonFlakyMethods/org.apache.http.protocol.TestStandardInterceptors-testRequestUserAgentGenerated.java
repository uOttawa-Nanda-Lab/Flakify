@Test public void testRequestUserAgentGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  request.getParams().setParameter(CoreProtocolPNames.USER_AGENT,"some agent");
  RequestUserAgent interceptor=new RequestUserAgent();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.USER_AGENT);
  Assert.assertNotNull(header);
  Assert.assertEquals("some agent",header.getValue());
}
