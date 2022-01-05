@Test public void testFindLevelWithThreadName() throws Exception {
  Log log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogLevel()).thenReturn(Level.ERROR);
  when(log.getLogThread()).thenReturn("thread-1");
  log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogLevel()).thenReturn(Level.ERROR);
  when(log.getLogThread()).thenReturn("thread-2");
  assertThat(LogUtil.findLevelWithThreadName(Level.ERROR,"thread-2"),equalTo(1));
}
