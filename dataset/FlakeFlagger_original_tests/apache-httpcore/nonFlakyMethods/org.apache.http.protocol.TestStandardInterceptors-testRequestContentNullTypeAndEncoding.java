@Test public void testRequestContentNullTypeAndEncoding() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  BasicHttpEntity entity=new BasicHttpEntity();
  request.setEntity(entity);
  RequestContent interceptor=new RequestContent();
  interceptor.process(request,context);
  Assert.assertNull(request.getFirstHeader(HTTP.CONTENT_TYPE));
  Assert.assertNull(request.getFirstHeader(HTTP.CONTENT_ENCODING));
}
