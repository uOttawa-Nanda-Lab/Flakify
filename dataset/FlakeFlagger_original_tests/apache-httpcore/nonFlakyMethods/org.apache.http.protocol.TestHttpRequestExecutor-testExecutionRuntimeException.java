@Test public void testExecutionRuntimeException() throws Exception {
  HttpClientConnection conn=Mockito.mock(HttpClientConnection.class);
  HttpRequestExecutor executor=new HttpRequestExecutor();
  HttpContext context=new BasicHttpContext();
  HttpRequest request=new BasicHttpRequest("GET","/");
  Mockito.doThrow(new RuntimeException("Oopsie")).when(conn).receiveResponseHeader();
  try {
    executor.execute(request,conn,context);
    Assert.fail("IOException should have been thrown");
  }
 catch (  RuntimeException ex) {
    Mockito.verify(conn).close();
    Assert.assertEquals(Boolean.TRUE,context.getAttribute(ExecutionContext.HTTP_REQ_SENT));
  }
}
