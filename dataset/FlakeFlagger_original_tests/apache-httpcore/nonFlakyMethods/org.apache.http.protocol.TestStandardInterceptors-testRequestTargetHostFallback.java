@Test public void testRequestTargetHostFallback() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  InetAddress address=Mockito.mock(InetAddress.class);
  Mockito.when(address.getHostName()).thenReturn("somehost");
  HttpInetConnection conn=Mockito.mock(HttpInetConnection.class);
  Mockito.when(conn.getRemoteAddress()).thenReturn(address);
  Mockito.when(conn.getRemotePort()).thenReturn(1234);
  context.setAttribute(ExecutionContext.HTTP_TARGET_HOST,null);
  context.setAttribute(ExecutionContext.HTTP_CONNECTION,conn);
  RequestTargetHost interceptor=new RequestTargetHost();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.TARGET_HOST);
  Assert.assertNotNull(header);
  Assert.assertEquals("somehost:1234",header.getValue());
}
