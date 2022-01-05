@Test public void testRequestTargetHostGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpHost host=new HttpHost("somehost",8080,"http");
  context.setAttribute(ExecutionContext.HTTP_TARGET_HOST,host);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  RequestTargetHost interceptor=new RequestTargetHost();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.TARGET_HOST);
  Assert.assertNotNull(header);
  Assert.assertEquals("somehost:8080",header.getValue());
}
