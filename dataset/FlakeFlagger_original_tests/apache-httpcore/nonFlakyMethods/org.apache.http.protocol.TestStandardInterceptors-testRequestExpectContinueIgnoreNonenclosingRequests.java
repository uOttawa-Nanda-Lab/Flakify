@Test public void testRequestExpectContinueIgnoreNonenclosingRequests() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("POST","/");
  RequestExpectContinue interceptor=new RequestExpectContinue();
  interceptor.process(request,context);
  Assert.assertEquals(0,request.getAllHeaders().length);
}
