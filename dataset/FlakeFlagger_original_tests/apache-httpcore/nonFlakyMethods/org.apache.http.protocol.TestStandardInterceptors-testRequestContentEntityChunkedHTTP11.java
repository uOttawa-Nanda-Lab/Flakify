@Test public void testRequestContentEntityChunkedHTTP11() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  String s="whatever";
  StringEntity entity=new StringEntity(s,"US-ASCII");
  entity.setChunked(true);
  request.setEntity(entity);
  RequestContent interceptor=new RequestContent();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.TRANSFER_ENCODING);
  Assert.assertNotNull(header);
  Assert.assertEquals("chunked",header.getValue());
  Assert.assertNull(request.getFirstHeader(HTTP.CONTENT_LEN));
}
