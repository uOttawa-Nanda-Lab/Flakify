@Test public void testRequestUserAgentMissingUserAgent() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  RequestUserAgent interceptor=new RequestUserAgent();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.USER_AGENT);
  Assert.assertNull(header);
}
