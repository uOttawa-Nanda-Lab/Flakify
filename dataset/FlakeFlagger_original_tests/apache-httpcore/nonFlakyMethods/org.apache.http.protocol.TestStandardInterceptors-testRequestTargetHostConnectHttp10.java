@Test public void testRequestTargetHostConnectHttp10() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpHost host=new HttpHost("somehost",8080,"http");
  context.setAttribute(ExecutionContext.HTTP_TARGET_HOST,host);
  BasicHttpRequest request=new BasicHttpRequest("CONNECT","/",HttpVersion.HTTP_1_0);
  RequestTargetHost interceptor=new RequestTargetHost();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.TARGET_HOST);
  Assert.assertNull(header);
}
