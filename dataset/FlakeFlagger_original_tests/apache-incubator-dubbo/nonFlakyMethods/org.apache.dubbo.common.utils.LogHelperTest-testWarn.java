@Test public void testWarn() throws Exception {
  Logger logger=Mockito.mock(Logger.class);
  when(logger.isWarnEnabled()).thenReturn(true);
  LogHelper.warn(logger,"warn");
  verify(logger).warn("warn");
  Throwable t=new RuntimeException();
  LogHelper.warn(logger,t);
  verify(logger).warn(t);
  LogHelper.warn(logger,"warn",t);
  verify(logger).warn("warn",t);
}
