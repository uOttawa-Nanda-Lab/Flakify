@Test(expected=ProtocolException.class) public void testRequestTargetHostFallbackFailure() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  HttpInetConnection conn=Mockito.mock(HttpInetConnection.class);
  Mockito.when(conn.getRemoteAddress()).thenReturn(null);
  Mockito.when(conn.getRemotePort()).thenReturn(1234);
  context.setAttribute(ExecutionContext.HTTP_TARGET_HOST,null);
  context.setAttribute(ExecutionContext.HTTP_CONNECTION,conn);
  RequestTargetHost interceptor=new RequestTargetHost();
  interceptor.process(request,context);
}
