@Test public void testRequestDateNotGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  RequestDate interceptor=new RequestDate();
  interceptor.process(request,context);
  Header h1=request.getFirstHeader(HTTP.DATE_HEADER);
  Assert.assertNull(h1);
}
