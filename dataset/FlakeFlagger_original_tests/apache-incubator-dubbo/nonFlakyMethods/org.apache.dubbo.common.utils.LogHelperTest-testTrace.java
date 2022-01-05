@Test public void testTrace() throws Exception {
  Logger logger=Mockito.mock(Logger.class);
  when(logger.isTraceEnabled()).thenReturn(true);
  LogHelper.trace(logger,"trace");
  verify(logger).trace("trace");
  Throwable t=new RuntimeException();
  LogHelper.trace(logger,t);
  verify(logger).trace(t);
  LogHelper.trace(logger,"trace",t);
  verify(logger).trace("trace",t);
}
