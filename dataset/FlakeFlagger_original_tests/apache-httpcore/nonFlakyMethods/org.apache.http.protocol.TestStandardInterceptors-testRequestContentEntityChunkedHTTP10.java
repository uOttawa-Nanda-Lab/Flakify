@Test public void testRequestContentEntityChunkedHTTP10() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/",HttpVersion.HTTP_1_0);
  String s="whatever";
  StringEntity entity=new StringEntity(s,"US-ASCII");
  entity.setChunked(true);
  request.setEntity(entity);
  RequestContent interceptor=new RequestContent();
  try {
    interceptor.process(request,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
