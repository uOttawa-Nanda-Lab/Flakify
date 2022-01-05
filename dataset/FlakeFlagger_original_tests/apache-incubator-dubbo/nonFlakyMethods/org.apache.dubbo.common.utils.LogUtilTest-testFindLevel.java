@Test public void testFindLevel() throws Exception {
  Log log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogLevel()).thenReturn(Level.ERROR);
  assertThat(LogUtil.findLevel(Level.ERROR),equalTo(1));
  assertThat(LogUtil.findLevel(Level.INFO),equalTo(0));
}
