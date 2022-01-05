@Test public void testRequestContentProtocolException() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request1=new BasicHttpEntityEnclosingRequest("POST","/");
  request1.addHeader(new BasicHeader(HTTP.TRANSFER_ENCODING,"chunked"));
  BasicHttpRequest request2=new BasicHttpEntityEnclosingRequest("POST","/");
  request2.addHeader(new BasicHeader(HTTP.CONTENT_LEN,"12"));
  RequestContent interceptor=new RequestContent();
  try {
    interceptor.process(request1,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
  try {
    interceptor.process(request2,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
