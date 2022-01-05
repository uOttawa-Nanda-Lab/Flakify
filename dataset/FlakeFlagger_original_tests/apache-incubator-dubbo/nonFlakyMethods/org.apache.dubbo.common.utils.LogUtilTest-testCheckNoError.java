@Test public void testCheckNoError() throws Exception {
  Log log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogLevel()).thenReturn(Level.ERROR);
  assertThat(LogUtil.checkNoError(),is(false));
  when(log.getLogLevel()).thenReturn(Level.INFO);
  assertThat(LogUtil.checkNoError(),is(true));
}
