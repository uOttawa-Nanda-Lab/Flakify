@Test public void testWholeCycle() throws JoranException {
  String mdcKey="cycle";
  configure(SIFT_FOLDER_PREFIX + "completeCycle.xml");
  MDC.put(mdcKey,"a");
  logger.debug("smoke");
  long timestamp=System.currentTimeMillis();
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  ListAppender<ILoggingEvent> listAppender=(ListAppender<ILoggingEvent>)sa.getAppenderTracker().find("a");
  assertNotNull(listAppender);
  List<ILoggingEvent> eventList=listAppender.list;
  assertEquals(1,listAppender.list.size());
  assertEquals("smoke",eventList.get(0).getMessage());
  MDC.remove(mdcKey);
  LoggingEvent le=new LoggingEvent("x",logger,Level.INFO,"hello",null,null);
  le.setTimeStamp(timestamp + ComponentTracker.DEFAULT_TIMEOUT + 1);
  sa.doAppend(le);
  assertFalse(listAppender.isStarted());
  assertEquals(1,sa.getAppenderTracker().allKeys().size());
  assertTrue(sa.getAppenderTracker().allKeys().contains("cycleDefault"));
}
