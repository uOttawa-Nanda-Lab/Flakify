@Test public void testFindMessage2() throws Exception {
  Log log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogMessage()).thenReturn("message");
  when(log.getLogLevel()).thenReturn(Level.ERROR);
  log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogMessage()).thenReturn("message");
  when(log.getLogLevel()).thenReturn(Level.INFO);
  assertThat(LogUtil.findMessage(Level.ERROR,"message"),equalTo(1));
}
