@Test public void testError() throws Exception {
  Logger logger=Mockito.mock(Logger.class);
  when(logger.isErrorEnabled()).thenReturn(true);
  LogHelper.error(logger,"error");
  verify(logger).error("error");
  Throwable t=new RuntimeException();
  LogHelper.error(logger,t);
  verify(logger).error(t);
  LogHelper.error(logger,"error",t);
  verify(logger).error("error",t);
}
