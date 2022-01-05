@Test public void testAppend() throws Exception {
  DubboAppender appender=new DubboAppender();
  appender.append(event);
  assertThat(DubboAppender.logList,hasSize(0));
  DubboAppender.doStart();
  appender.append(event);
  assertThat(DubboAppender.logList,hasSize(1));
  Log log=DubboAppender.logList.get(0);
  assertThat(log.getLogThread(),equalTo("thread-name"));
}
