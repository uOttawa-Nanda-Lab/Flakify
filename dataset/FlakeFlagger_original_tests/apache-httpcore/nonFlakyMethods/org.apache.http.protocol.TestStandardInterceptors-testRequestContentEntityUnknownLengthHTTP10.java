@Test public void testRequestContentEntityUnknownLengthHTTP10() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/",HttpVersion.HTTP_1_0);
  BasicHttpEntity entity=new BasicHttpEntity();
  entity.setContentLength(-1);
  entity.setChunked(false);
  request.setEntity(entity);
  RequestContent interceptor=new RequestContent();
  try {
    interceptor.process(request,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
