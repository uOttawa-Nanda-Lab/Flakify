@Test public void testDebug() throws Exception {
  Logger logger=Mockito.mock(Logger.class);
  when(logger.isDebugEnabled()).thenReturn(true);
  LogHelper.debug(logger,"debug");
  verify(logger).debug("debug");
  Throwable t=new RuntimeException();
  LogHelper.debug(logger,t);
  verify(logger).debug(t);
  LogHelper.debug(logger,"debug",t);
  verify(logger).debug("debug",t);
}
