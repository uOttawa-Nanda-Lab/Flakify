@Test public void testConnectionKeepAlive() throws Exception {
  HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);
  ConnectionReuseStrategy connReuseStrategy=Mockito.mock(ConnectionReuseStrategy.class);
  HttpResponseFactory responseFactory=Mockito.mock(HttpResponseFactory.class);
  HttpRequestHandlerResolver handlerResolver=Mockito.mock(HttpRequestHandlerResolver.class);
  HttpRequestHandler requestHandler=Mockito.mock(HttpRequestHandler.class);
  HttpParams params=new SyncBasicHttpParams();
  HttpService httpservice=new HttpService(httprocessor,connReuseStrategy,responseFactory,handlerResolver,params);
  HttpContext context=new BasicHttpContext();
  HttpServerConnection conn=Mockito.mock(HttpServerConnection.class);
  HttpRequest request=new BasicHttpRequest("GET","/");
  Mockito.when(conn.receiveRequestHeader()).thenReturn(request);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  Mockito.when(responseFactory.newHttpResponse(HttpVersion.HTTP_1_1,200,context)).thenReturn(response);
  Mockito.when(handlerResolver.lookup("/")).thenReturn(requestHandler);
  Mockito.when(connReuseStrategy.keepAlive(response,context)).thenReturn(true);
  httpservice.handleRequest(conn,context);
  Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
  Assert.assertSame(conn,context.getAttribute(ExecutionContext.HTTP_CONNECTION));
  Assert.assertSame(request,context.getAttribute(ExecutionContext.HTTP_REQUEST));
  Assert.assertSame(response,context.getAttribute(ExecutionContext.HTTP_RESPONSE));
  Mockito.verify(httprocessor).process(request,context);
  Mockito.verify(httprocessor).process(response,context);
  Mockito.verify(conn).sendResponseHeader(response);
  Mockito.verify(conn).sendResponseEntity(response);
  Mockito.verify(conn).flush();
  Mockito.verify(conn,Mockito.never()).close();
}
