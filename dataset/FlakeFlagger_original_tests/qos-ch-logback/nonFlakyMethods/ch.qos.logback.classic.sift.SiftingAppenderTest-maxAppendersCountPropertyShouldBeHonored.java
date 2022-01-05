@Test public void maxAppendersCountPropertyShouldBeHonored() throws JoranException {
  configure(SIFT_FOLDER_PREFIX + "maxAppenderCount.xml");
  int max=5;
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  String mdcKey="max";
  for (int i=0; i <= max; i++) {
    MDC.put(mdcKey,"" + (diff + i));
    LoggingEvent event=new LoggingEvent("",logger,Level.DEBUG,"max" + i,null,null);
    event.setTimeStamp(now);
    sa.doAppend(event);
    now+=AbstractComponentTracker.WAIT_BETWEEN_SUCCESSIVE_REMOVAL_ITERATIONS;
  }
  AppenderTracker<ILoggingEvent> tracker=sa.getAppenderTracker();
  assertEquals(max,tracker.allKeys().size());
  assertNull(tracker.find("" + (diff + 0)));
  for (int i=1; i <= max; i++) {
    assertNotNull(tracker.find("" + (diff + i)));
  }
}
