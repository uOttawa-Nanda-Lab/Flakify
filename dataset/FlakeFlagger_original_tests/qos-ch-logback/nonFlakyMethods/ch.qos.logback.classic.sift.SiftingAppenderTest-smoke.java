@Test public void smoke() throws JoranException {
  configure(SIFT_FOLDER_PREFIX + "smoke.xml");
  logger.debug("smoke");
  Appender<ILoggingEvent> appender=getAppenderTracker().find("smokeDefault");
  assertNotNull(appender);
  ListAppender<ILoggingEvent> listAppender=(ListAppender<ILoggingEvent>)appender;
  List<ILoggingEvent> eventList=listAppender.list;
  assertEquals(1,listAppender.list.size());
  assertEquals("smoke",eventList.get(0).getMessage());
}
