@Test public void testExecutionHead() throws Exception {
  HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);
  HttpClientConnection conn=Mockito.mock(HttpClientConnection.class);
  HttpRequestExecutor executor=new HttpRequestExecutor();
  HttpContext context=new BasicHttpContext();
  HttpRequest request=new BasicHttpRequest("HEAD","/");
  executor.preProcess(request,httprocessor,context);
  Mockito.verify(httprocessor).process(request,context);
  Mockito.when(conn.receiveResponseHeader()).thenReturn(new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK"));
  HttpResponse response=executor.execute(request,conn,context);
  Mockito.verify(conn).sendRequestHeader(request);
  Mockito.verify(conn).flush();
  Mockito.verify(conn).receiveResponseHeader();
  Mockito.verify(conn,Mockito.never()).receiveResponseEntity(response);
  executor.postProcess(response,httprocessor,context);
  Mockito.verify(httprocessor).process(response,context);
}
