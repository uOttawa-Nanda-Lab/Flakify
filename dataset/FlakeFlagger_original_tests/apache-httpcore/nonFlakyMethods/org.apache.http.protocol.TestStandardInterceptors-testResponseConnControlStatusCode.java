@Test public void testResponseConnControlStatusCode() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  request.addHeader(new BasicHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE));
  context.setAttribute(ExecutionContext.HTTP_REQUEST,request);
  ResponseConnControl interceptor=new ResponseConnControl();
  int[] statusCodes=new int[]{HttpStatus.SC_BAD_REQUEST,HttpStatus.SC_REQUEST_TIMEOUT,HttpStatus.SC_LENGTH_REQUIRED,HttpStatus.SC_REQUEST_TOO_LONG,HttpStatus.SC_REQUEST_URI_TOO_LONG,HttpStatus.SC_SERVICE_UNAVAILABLE,HttpStatus.SC_NOT_IMPLEMENTED};
  for (int i=0; i < statusCodes.length; i++) {
    BasicHttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,statusCodes[i],"Unreasonable");
    interceptor.process(response,context);
    Header header=response.getFirstHeader(HTTP.CONN_DIRECTIVE);
    Assert.assertNotNull(header);
    Assert.assertEquals(HTTP.CONN_CLOSE,header.getValue());
  }
}
