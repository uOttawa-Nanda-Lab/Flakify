@Test public void testRequestContentNullEntity() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  RequestContent interceptor=new RequestContent();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNotNull(header);
  Assert.assertEquals("0",header.getValue());
  Assert.assertNull(request.getFirstHeader(HTTP.TRANSFER_ENCODING));
}
