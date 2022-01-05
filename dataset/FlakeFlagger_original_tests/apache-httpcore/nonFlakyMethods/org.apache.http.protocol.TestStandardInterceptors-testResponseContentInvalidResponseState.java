@Test public void testResponseContentInvalidResponseState() throws Exception {
  ResponseContent interceptor=new ResponseContent();
  HttpContext context=new BasicHttpContext(null);
  try {
    HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
    response.addHeader(new BasicHeader(HTTP.CONTENT_LEN,"10"));
    interceptor.process(response,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
  try {
    HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
    response.addHeader(new BasicHeader(HTTP.TRANSFER_ENCODING,"stuff"));
    interceptor.process(response,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
