@Test public void testExecutionEntityEnclosingRequestUnsupportedIntermediateResponse() throws Exception {
  HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);
  HttpClientConnection conn=Mockito.mock(HttpClientConnection.class);
  HttpRequestExecutor executor=new HttpRequestExecutor();
  HttpContext context=new BasicHttpContext();
  HttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  request.addHeader(HTTP.EXPECT_DIRECTIVE,HTTP.EXPECT_CONTINUE);
  HttpEntity entity=Mockito.mock(HttpEntity.class);
  request.setEntity(entity);
  executor.preProcess(request,httprocessor,context);
  Mockito.verify(httprocessor).process(request,context);
  Mockito.when(conn.receiveResponseHeader()).thenReturn(new BasicHttpResponse(HttpVersion.HTTP_1_1,101,"OK"));
  Mockito.when(conn.isResponseAvailable(Mockito.anyInt())).thenReturn(true);
  try {
    executor.execute(request,conn,context);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
    Mockito.verify(conn).close();
    Assert.assertEquals(Boolean.FALSE,context.getAttribute(ExecutionContext.HTTP_REQ_SENT));
  }
}
