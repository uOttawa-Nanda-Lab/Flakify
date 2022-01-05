@Test public void testFindThread() throws Exception {
  Log log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogThread()).thenReturn("thread-1");
  assertThat(LogUtil.findThread("thread-1"),equalTo(1));
}
