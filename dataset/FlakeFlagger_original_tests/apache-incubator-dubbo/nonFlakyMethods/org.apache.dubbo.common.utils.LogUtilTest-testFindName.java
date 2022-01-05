@Test public void testFindName() throws Exception {
  Log log=mock(Log.class);
  DubboAppender.logList.add(log);
  when(log.getLogName()).thenReturn("a");
  assertThat(LogUtil.findName("a"),equalTo(1));
}
