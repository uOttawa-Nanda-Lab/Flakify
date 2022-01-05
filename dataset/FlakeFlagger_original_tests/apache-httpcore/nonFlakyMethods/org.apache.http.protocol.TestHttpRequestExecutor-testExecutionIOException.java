@Test public void testExecutionIOException() throws Exception {
  HttpClientConnection conn=Mockito.mock(HttpClientConnection.class);
  HttpRequestExecutor executor=new HttpRequestExecutor();
  HttpContext context=new BasicHttpContext();
  HttpRequest request=new BasicHttpRequest("GET","/");
  Mockito.doThrow(new IOException("Oopsie")).when(conn).sendRequestHeader(request);
  try {
    executor.execute(request,conn,context);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
    Mockito.verify(conn).close();
    Assert.assertEquals(Boolean.FALSE,context.getAttribute(ExecutionContext.HTTP_REQ_SENT));
  }
}
