@Test public void testExecutionEntityEclosingRequestCustomExpectationVerifier() throws Exception {
  HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);
  ConnectionReuseStrategy connReuseStrategy=Mockito.mock(ConnectionReuseStrategy.class);
  HttpResponseFactory responseFactory=Mockito.mock(HttpResponseFactory.class);
  HttpRequestHandlerResolver handlerResolver=Mockito.mock(HttpRequestHandlerResolver.class);
  HttpParams params=new SyncBasicHttpParams();
  HttpExpectationVerifier expectationVerifier=new HttpExpectationVerifier(){
    public void verify(    final HttpRequest request,    final HttpResponse response,    final HttpContext context) throws HttpException {
      response.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
    }
  }
;
  HttpService httpservice=new HttpService(httprocessor,connReuseStrategy,responseFactory,handlerResolver,expectationVerifier,params);
  HttpContext context=new BasicHttpContext();
  HttpServerConnection conn=Mockito.mock(HttpServerConnection.class);
  HttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");
  request.addHeader(HTTP.EXPECT_DIRECTIVE,HTTP.EXPECT_CONTINUE);
  InputStream instream=Mockito.mock(InputStream.class);
  InputStreamEntity entity=new InputStreamEntity(instream,-1);
  request.setEntity(entity);
  Mockito.when(conn.receiveRequestHeader()).thenReturn(request);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,100,"Continue");
  Mockito.when(responseFactory.newHttpResponse(HttpVersion.HTTP_1_1,100,context)).thenReturn(response);
  Mockito.when(connReuseStrategy.keepAlive(response,context)).thenReturn(false);
  httpservice.handleRequest(conn,context);
  Assert.assertSame(conn,context.getAttribute(ExecutionContext.HTTP_CONNECTION));
  Assert.assertSame(request,context.getAttribute(ExecutionContext.HTTP_REQUEST));
  Assert.assertSame(response,context.getAttribute(ExecutionContext.HTTP_RESPONSE));
  Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED,response.getStatusLine().getStatusCode());
  Mockito.verify(conn).sendResponseHeader(response);
  Mockito.verify(conn,Mockito.never()).receiveRequestEntity(request);
  Mockito.verify(httprocessor).process(response,context);
  Mockito.verify(conn).sendResponseHeader(response);
  Mockito.verify(conn).sendResponseEntity(response);
  Mockito.verify(conn).flush();
  Mockito.verify(conn).close();
}
