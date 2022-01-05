@Test public void testFindMessage1() throws Exception {
  Log log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogMessage()).thenReturn("message");
  assertThat(LogUtil.findMessage("message"),equalTo(1));
}
