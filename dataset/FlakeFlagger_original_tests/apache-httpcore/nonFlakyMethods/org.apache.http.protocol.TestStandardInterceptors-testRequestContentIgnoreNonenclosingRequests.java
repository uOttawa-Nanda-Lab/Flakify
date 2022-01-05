@Test public void testRequestContentIgnoreNonenclosingRequests() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("POST","/");
  RequestContent interceptor=new RequestContent();
  interceptor.process(request,context);
  Assert.assertEquals(0,request.getAllHeaders().length);
}
