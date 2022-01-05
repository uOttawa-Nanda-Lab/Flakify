@Test public void testRequestContentTypeAndEncoding() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  BasicHttpEntity entity=new BasicHttpEntity();
  entity.setContentType("whatever");
  entity.setContentEncoding("whatever");
  request.setEntity(entity);
  RequestContent interceptor=new RequestContent();
  interceptor.process(request,context);
  Header h1=request.getFirstHeader(HTTP.CONTENT_TYPE);
  Assert.assertNotNull(h1);
  Assert.assertEquals("whatever",h1.getValue());
  Header h2=request.getFirstHeader(HTTP.CONTENT_ENCODING);
  Assert.assertNotNull(h2);
  Assert.assertEquals("whatever",h2.getValue());
}
