@Test public void testRequestConnControlGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  RequestConnControl interceptor=new RequestConnControl();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.CONN_DIRECTIVE);
  Assert.assertNotNull(header);
  Assert.assertEquals(HTTP.CONN_KEEP_ALIVE,header.getValue());
}
