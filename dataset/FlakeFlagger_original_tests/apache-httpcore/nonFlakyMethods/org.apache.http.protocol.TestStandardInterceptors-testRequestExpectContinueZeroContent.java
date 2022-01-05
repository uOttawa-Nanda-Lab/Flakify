@Test public void testRequestExpectContinueZeroContent() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  String s="";
  StringEntity entity=new StringEntity(s,"US-ASCII");
  request.setEntity(entity);
  request.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,true);
  RequestExpectContinue interceptor=new RequestExpectContinue();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.EXPECT_DIRECTIVE);
  Assert.assertNull(header);
}
