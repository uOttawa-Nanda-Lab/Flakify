@Test public void testRequestDateGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  RequestDate interceptor=new RequestDate();
  interceptor.process(request,context);
  Header h1=request.getFirstHeader(HTTP.DATE_HEADER);
  Assert.assertNotNull(h1);
  interceptor.process(request,context);
  Header h2=request.getFirstHeader(HTTP.DATE_HEADER);
  Assert.assertNotNull(h2);
}
