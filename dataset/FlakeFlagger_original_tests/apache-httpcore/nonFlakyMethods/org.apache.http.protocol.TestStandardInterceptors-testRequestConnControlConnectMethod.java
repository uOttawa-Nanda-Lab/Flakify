@Test public void testRequestConnControlConnectMethod() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("CONNECT","/");
  RequestConnControl interceptor=new RequestConnControl();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.CONN_DIRECTIVE);
  Assert.assertNull(header);
}
