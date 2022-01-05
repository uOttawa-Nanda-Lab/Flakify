@Test public void testRequestContentEntityContentLengthDelimitedHTTP11() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  String s="whatever";
  StringEntity entity=new StringEntity(s,"US-ASCII");
  request.setEntity(entity);
  RequestContent interceptor=new RequestContent();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNotNull(header);
  Assert.assertEquals(s.length(),Integer.parseInt(header.getValue()));
  Assert.assertNull(request.getFirstHeader(HTTP.TRANSFER_ENCODING));
}
