@Test public void testInfo() throws Exception {
  Logger logger=Mockito.mock(Logger.class);
  when(logger.isInfoEnabled()).thenReturn(true);
  LogHelper.info(logger,"info");
  verify(logger).info("info");
  Throwable t=new RuntimeException();
  LogHelper.info(logger,t);
  verify(logger).info(t);
  LogHelper.info(logger,"info",t);
  verify(logger).info("info",t);
}
