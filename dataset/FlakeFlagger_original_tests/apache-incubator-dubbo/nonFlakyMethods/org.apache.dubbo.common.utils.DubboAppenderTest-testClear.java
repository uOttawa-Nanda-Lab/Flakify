@Test public void testClear() throws Exception {
  DubboAppender.doStart();
  DubboAppender appender=new DubboAppender();
  appender.append(event);
  assertThat(DubboAppender.logList,hasSize(1));
  DubboAppender.clear();
  assertThat(DubboAppender.logList,hasSize(0));
}
