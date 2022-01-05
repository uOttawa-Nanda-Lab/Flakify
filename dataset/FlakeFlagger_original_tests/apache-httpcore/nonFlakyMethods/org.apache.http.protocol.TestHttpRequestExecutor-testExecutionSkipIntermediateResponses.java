@Test public void testExecutionSkipIntermediateResponses() throws Exception {
  HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);
  HttpClientConnection conn=Mockito.mock(HttpClientConnection.class);
  HttpRequestExecutor executor=new HttpRequestExecutor();
  HttpContext context=new BasicHttpContext();
  HttpRequest request=new BasicHttpRequest("GET","/");
  executor.preProcess(request,httprocessor,context);
  Mockito.verify(httprocessor).process(request,context);
  Mockito.when(conn.receiveResponseHeader()).thenReturn(new BasicHttpResponse(HttpVersion.HTTP_1_1,100,"OK"),new BasicHttpResponse(HttpVersion.HTTP_1_1,101,"OK"),new BasicHttpResponse(HttpVersion.HTTP_1_1,102,"OK"),new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK"));
  HttpResponse response=executor.execute(request,conn,context);
  Mockito.verify(conn).sendRequestHeader(request);
  Mockito.verify(conn).flush();
  Mockito.verify(conn,Mockito.times(4)).receiveResponseHeader();
  Mockito.verify(conn,Mockito.times(1)).receiveResponseEntity(response);
  executor.postProcess(response,httprocessor,context);
  Mockito.verify(httprocessor).process(response,context);
  Assert.assertSame(conn,context.getAttribute(ExecutionContext.HTTP_CONNECTION));
  Assert.assertSame(request,context.getAttribute(ExecutionContext.HTTP_REQUEST));
  Assert.assertSame(response,context.getAttribute(ExecutionContext.HTTP_RESPONSE));
}
