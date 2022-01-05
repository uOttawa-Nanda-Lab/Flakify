@Test public void testExecutionEntityEnclosingRequestWithExpectContinueSuccess() throws Exception {
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
  Mockito.when(conn.receiveResponseHeader()).thenReturn(new BasicHttpResponse(HttpVersion.HTTP_1_1,100,"Continue"),new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK"));
  Mockito.when(conn.isResponseAvailable(Mockito.anyInt())).thenReturn(true);
  HttpResponse response=executor.execute(request,conn,context);
  Mockito.verify(conn).sendRequestHeader(request);
  Mockito.verify(conn).sendRequestEntity(request);
  Mockito.verify(conn,Mockito.times(2)).flush();
  Mockito.verify(conn).isResponseAvailable(2000);
  Mockito.verify(conn,Mockito.times(2)).receiveResponseHeader();
  Mockito.verify(conn).receiveResponseEntity(response);
  executor.postProcess(response,httprocessor,context);
  Mockito.verify(httprocessor).process(response,context);
  Assert.assertEquals(Boolean.TRUE,context.getAttribute(ExecutionContext.HTTP_REQ_SENT));
}
