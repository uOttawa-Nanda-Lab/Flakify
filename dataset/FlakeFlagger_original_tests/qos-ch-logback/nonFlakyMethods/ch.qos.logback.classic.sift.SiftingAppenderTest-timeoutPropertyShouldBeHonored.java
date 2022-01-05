@Test public void timeoutPropertyShouldBeHonored() throws JoranException, InterruptedException {
  configure(SIFT_FOLDER_PREFIX + "timeout.xml");
  long timeout=30 * 1000;
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  LoggingEvent event=new LoggingEvent("",logger,Level.DEBUG,"timeout",null,null);
  event.setTimeStamp(now);
  sa.doAppend(event);
  AppenderTracker<ILoggingEvent> tracker=sa.getAppenderTracker();
  assertEquals(1,tracker.getComponentCount());
  now+=timeout + 1;
  tracker.removeStaleComponents(now);
  assertEquals(0,tracker.getComponentCount());
  statusChecker.assertIsErrorFree();
}
